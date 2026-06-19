# Web Security Header Analyzer 

A pure Java console application that evaluates web pages for critical configuration vulnerabilities. By analyzing server responses and HTTP security headers, this tool identifies protocol weaknesses, clickjacking exposures, and sensitive information leakage.

---

##  Core Features

* **Smart URL Validation:** Automatically normalizes inputs (e.g., adds `http://` if missing) and validates the URL syntax prior to scanning to ensure stability.
* **Data Transmission Audit:** Evaluates whether connection protocols use transport layer encryption (`HTTPS/TLS`) to safeguard data from sniffing attacks.
* **UI Integrity (Clickjacking) Assessment:** Checks for the presence of the `X-Frame-Options` header to determine if the page can be unsafely embedded inside malicious `<iframe>` tags.
* **Information Disclosure Detection:** Scans for exposed `Server` banners that leak software names or versions, which could be utilized by attackers to search for specific exploits.

---

##  Requirements & Tech Stack

* **Language:** Java (JDK 8 or higher)
* **Dependencies:** None! Built entirely using Java's native standard libraries (`java.net.HttpURLConnection`, `java.util.Scanner`).

---

## 📁 Project Structure

```text
└── SecurityScanner.java     # Main application file containing validation and scanning logic
