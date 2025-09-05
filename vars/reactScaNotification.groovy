def call() {
    def jobName = env.JOB_NAME
    def buildNum = env.BUILD_NUMBER
    def buildUrl = env.BUILD_URL
    def status = currentBuild.currentResult

    if (status == 'SUCCESS') {
        emailext(
            to: params.RECIPIENT_EMAIL,
            subject: "SUCCESS: ${jobName} #${buildNum}",
            body: "The build was successful  | <a href='${buildUrl}'>Click here to view logs</a>"
        )

        slackSend(
            channel: params.SLACK_CHANNEL,
            message: " SUCCESS: ${jobName} #${buildNum} | <${buildUrl}|Click here to view logs>"
        )

    } else {
        emailext(
            to: params.RECIPIENT_EMAIL,
            subject: "FAILED: ${jobName} #${buildNum}",
            body: "The build failed  | <a href='${buildUrl}'>Click here to view logs</a>"
        )

        slackSend(
            channel: params.SLACK_CHANNEL,
            message: " FAILED: ${jobName} #${buildNum} | <${buildUrl}|Click here to view logs>"
        )
    }
}
