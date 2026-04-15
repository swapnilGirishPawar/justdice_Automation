# justDice Automation

Mobile UI automation for the **Cash Giraffe** Android app using **Appium 2**, **TestNG**, and **Maven**.

## Introduction
This repository is a **sample mobile test automation framework** built for **[justdice.io](https://justdice.io)**. The framework was created **on my own initiative** to show how I would structure, run, and report UI checks for a product in their ecosystem - in this case the **Cash Giraffe** Android app.
The goal is to make it easy for reviewers to see **real automation choices** (Page Object Model, TestNG lifecycle, Appium 2, ExtentReports) rather than only discussing them in interviews. If you are from justDice and reviewing this: thank you for the time in the first round; I hope this gives a concrete picture of how I work with mobile stacks and maintainable test code.


## Framework overview

The project follows a layered layout: **Page Object Model** for locators and actions, a **base hook** class for driver lifecycle and reporting, and **TestNG** for grouping and ordering tests. The Appium **Java client** drives **UiAutomator2** on Android. **ExtentReports** (Spark) produces HTML reports under `reports/`.

## What is included

| Area | Description |
|------|-------------|
| **Build** | Maven (`pom.xml`), Java 23 |
| **Test runner** | TestNG 7.12 |
| **Mobile** | Appium Java client 10, `AndroidDriver` + `UiAutomator2Options` |
| **Reporting** | ExtentReports 5 (`ExtentSparkReporter`), timestamped HTML in `reports/` |
| **Base setup** | `BaseClass.Hooks` — starts reporting and app before tests, quits driver and flushes report after |
| **Shared utilities** | `Utils.CommonUtils` — driver `ThreadLocal`, launch/teardown, Extent helpers, `isDisplayed` guard |
| **Page objects** | `Pages.Login.LoginPage`, `Pages.Discover.DiscoverPage`, `Pages.Profile.ProfilePage` |
| **Tests** | `TestSuite.LoginTests`, `TestSuite.ProfileTests`, `TestSuite.DiscoverTests` |
| **App metadata** | `src/test/java/Resources/appFile/README.md` — app store link; APK is not stored in the repo |

## Prerequisites

- JDK 23 (matches `pom.xml` compiler settings)
- **Appium 2** server listening on `http://127.0.0.1:4723/` (see `CommonUtils.launchApp()`)
- Android device or emulator with the Cash Giraffe app installed (`appPackage` is `cashgiraffe.app`)
- Device UDID must match the value used in code: `CommonUtils` currently sets `options.setUdid("emulator-5554")` — change this if you use another device (the `androidDeviceName` parameter in `testng.xml` is not wired into that class today)

## Project layout

```text
src/main/java/
  Pages/          # Page objects (Login, Discover, Profile)
  Utils/          # CommonUtils — driver, Extent, app launch
src/test/java/
  BaseClass/      # Hooks — TestNG lifecycle + reporting
  TestSuite/      # Test classes by feature
  Resources/      # App notes (Play Store APK Link is provided)
testng.xml        # TestNG suite definition
reports/          # Generated Extent HTML reports (created at run time)
```

## Running tests with `testng.xml`

The suite file [`testng.xml`](testng.xml) defines:

- **Suite name**: `Just Dice Sanity Test Suite - UI Flows`
- **Test name**: `Just Dice - Cash Giraffee Automation Local Execution`
- **Classes** (order matters for shared `@BeforeTest` in `Hooks`):
  1. `BaseClass.Hooks` — must run first so the app session and Extent report are initialized
  2. `TestSuite.LoginTests.LoginTests`
  3. `TestSuite.ProfileTests.ProfileTests`

`TestSuite.DiscoverTests.DiscoverTests` exists in the repo but is **not** listed in `testng.xml`. To include it, add:

```xml
<class name="TestSuite.DiscoverTests.DiscoverTests"/>
```

inside the `<classes>` block.

### IntelliJ IDEA

1. Open the project as a Maven project.
2. Right-click `testng.xml` → **Run** (or **Modify Run Configuration** → TestNG → Suite: `testng.xml`).

### Eclipse / other IDEs

Run the file as a **TestNG Suite**, pointing to `testng.xml`.

### Command line (Maven)

This `pom.xml` does not declare the TestNG suite for Surefire. To run the same suite from the CLI, add `maven-surefire-plugin` with `suiteXmlFiles` set to `testng.xml`, then run:

```bash
mvn test
```

## Reports

After a run, open the newest file under `reports/`, named like:

`Android_Cash Giraffe_<dd-MM-yyyy_HH:mm:ss>.html`

## Configuration checklist

1. Start Appium on port **4723**.
2. Ensure the emulator/device UDID matches `CommonUtils.launchApp()`.
3. Install the Cash Giraffe app on the device (package `cashgiraffe.app`).
4. Run the suite via `testng.xml` as above.
