def call() {

    runTests()

    archiveReports()
}



def runTests() {
    sh 'mvn clean test'
}


def archiveReports() {
    junit '**/target/surefire-reports/*.xml'
    archiveArtifacts artifacts: 'target/**/*.jar, target/surefire-reports/*.*', fingerprint: true
}
