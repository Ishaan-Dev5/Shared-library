def call(){
def goHome = tool name: 'GoLang', type: 'go'
    env.PATH = "${goHome}/bin:${env.PATH}"
    
  try {
        snykSecurity(
            snykInstallation: 'snyk_tool',   // Jenkins me configured Snyk installation
            snykTokenId: 'SNYK_API',     
            report: true,
    reportFile: "${WORKSPACE}/snyk-report.html",
    failOnIssues: false
        )
    } catch (err) {
        echo "Snyk scan detected vulnerabilities, but pipeline will continue."
        echo "Error details: ${err}"
    }
}

  
