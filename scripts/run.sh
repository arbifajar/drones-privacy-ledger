#!/usr/bin/env bash
set -euo pipefail
mvn -q clean package
java -jar target/drone-privacy-ledger-0.1.0.jar
