#!/bin/bash

# Mobile Automation Framework - Easy Run Script
# This script helps you run your project without errors

echo "🚀 Mobile Automation Framework - Run Options"
echo "=============================================="
echo ""
echo "Choose what you want to do:"
echo "1. Compile project (no tests) - RECOMMENDED"
echo "2. Run tests"
echo "3. Build package (skip tests)"
echo "4. Full build with tests"
echo "5. Show all available commands"
echo ""
read -p "Enter your choice (1-5): " choice

case $choice in
    1)
        echo "📦 Compiling project..."
        mvn clean compile test-compile
        ;;
    2)
        echo "🧪 Running tests..."
        mvn clean test
        ;;
    3)
        echo "📦 Building package (skipping tests)..."
        mvn clean package -DskipTests
        ;;
    4)
        echo "🔨 Full build with tests..."
        mvn clean verify
        ;;
    5)
        echo "📋 Available Maven Commands:"
        echo "=========================="
        echo "mvn clean compile test-compile  (compile only)"
        echo "mvn clean test                  (run tests)"
        echo "mvn clean package -DskipTests   (build without tests)"
        echo "mvn clean verify                (full build)"
        echo "mvn clean install -DskipTests   (install without tests)"
        ;;
    *)
        echo "❌ Invalid choice. Please run the script again."
        ;;
esac

echo ""
echo "✅ Done! Check the output above for results."
