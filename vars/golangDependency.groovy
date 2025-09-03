def call(String odcInstallation) {
    def nvdDbPath  = '/var/lib/jenkins/shared/nvd-data'
    def reportDir  = 'dependency-check-report'
    def reportFile = 'dc-report.html'
    def projectName = 'Golang'

    
    sh "mkdir -p ${nvdDbPath}"

    
    dependencyCheck odcInstallation: odcInstallation, 
                    additionalArguments: "--updateonly --data ${nvdDbPath} || true"

    
    sh "mkdir -p ${reportDir}"

    
    dependencyCheck odcInstallation: odcInstallation, 
                    additionalArguments: "--data ${nvdDbPath} --scan . --project ${projectName} --format HTML --out ${reportDir}/${reportFile}"

    
    archiveArtifacts artifacts: "${reportDir}/${reportFile}", allowEmptyArchive: true
    publishHTML(target: [
        allowMissing: false,
        alwaysLinkToLastBuild: true,
        keepAll: true,
        reportDir: reportDir,
        reportFiles: reportFile,
        reportName: 'Dependency-Check Report'
    ])
}
