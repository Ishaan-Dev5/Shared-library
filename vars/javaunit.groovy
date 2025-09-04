def call() {
    // Clone the repo
    git url: 'https://github.com/OT-MICROSERVICES/salary-api.git', branch: 'main'

    echo "Running unit tests"
    sh 'mvn test -Dgroups="!integration"' 

    echo "Archiving test reports"
    archiveArtifacts artifacts: 'target/surefire-reports/*.xml', fingerprint: true

    echo "Unit tests completed successfully and reports archived!"
}
