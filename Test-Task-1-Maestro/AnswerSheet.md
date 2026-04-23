# 📄 Test Design Decisions – Maestro Login Flows

## 1. Why I Chose These Selectors
The selectors used in the flows are primarily based on:
- **Element IDs (`id`)** wherever available (e.g., `loginScreen.emailInput`, `loginScreen.loginButton`)
- **Visible text** as a fallback (e.g., `"Log in"`, `"Email"`)

### Rationale:
- **Stability**: IDs are more stable than text.
- **Readability**: Using meaningful IDs improves test clarity and maintainability.
- **Fallback Safety**: In cases if IDs were not available, visible text was used to ensure coverage.


---

## 2. Why These Assertions Are Reliable
The assertions focus on:
- **UI visibility checks** (`assertVisible`)
- **State validation** (e.g., login button enabled/disabled)
- **Post-login navigation validation** (e.g., Home screen)

### Rationale:
- Validation is done at **key checkpoints**:
    - Before input (initial UI state)
    - After input (state changes)
    - After action (successful navigation)

This ensures the test reflects **real user behavior and expectations**, making it reliable.

---

## 3. What Could Make This Test Flaky
Potential causes of flakiness include:

- **Network dependency**
    - Slow or unstable network may delay login response

- **Timing issues**
    - UI elements may take time to render after actions

- **Dynamic content**
    - Changes in UI text, IDs, or structure


- **Hardcoded test data**
    - Example: fixed email/password might fail if the account state changes

---

## 4. How I Would Improve This in a Real Project

### 1. Add Explicit Waits / Synchronization
- Use smarter waiting strategies instead of immediate assertions
- Wait for elements based on **state or API completion**

---

### 2. Improve Selector Strategy
- Collaborate with developers to add:
    - **Test IDs (data-testid)** specifically for automation
- Reduce dependency on text-based selectors

---

### 3. Add Negative & Edge Case Tests
- Invalid login scenarios
- Empty input validation
- Error message verification

---

### 4. Integrate with CI/CD
- Run flows on every PR using Maestro CLI or Maestro Cloud (Currently able to run in Maestro Cloud)

---

### 5. Parallel Execution & Reporting
- Execute tests in parallel (works only on cloud, not local)
- Add reporting dashboards for better visibility

---

### 6. Modularize Flows Further
- Reuse components like:
    - Login steps
    - App launch
- Improve maintainability and scalability

---

## 📌 Summary
The current implementation focuses on **clarity, stability, and quick setup**, given the short timeframe. With more time and in a production setup, the test suite can be enhanced significantly in terms of **scalability, reliability, and coverage**.