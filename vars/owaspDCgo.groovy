def call() {
    echo "Running unit tests in current directory"

    try {
        // Hardcoded command for unit tests
        sh 'mvn clean test'
        echo "Unit tests completed successfully."
    } catch (err) {
        echo "Unit tests failed!"
        error("Stopping pipeline due to failed tests.")
    }
}
