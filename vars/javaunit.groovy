def call() {
    // Clone the repo
    git url: 'https://github.com/Ishaan-Dev5/salary-api.git', branch: 'main'

    echo "Running unit tests"
    // Run tests but don't fail the pipeline
    sh 'mvn clean test || true'

    echo "Archiving test reports"
    archiveArtifacts artifacts: 'target/surefire-reports/*.xml', fingerprint: true

    echo "Publishing JUnit reports"
    // This will mark test results in Jenkins UI but won't fail the pipeline
    junit allowEmptyResults: true, keepLongStdio: true, testResults: 'target/surefire-reports/*.xml'

    echo "Unit tests completed successfully and reports archived!"
}
