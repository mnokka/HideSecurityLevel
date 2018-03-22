(function ($) {
    $(function () {
        /**
         * FROM https://scriptrunner.adaptavist.com/latest/jira/fragments/WebResource.html
         * 
         * Binding is required for link such as
         * http://localhost:8080/jira/projects/JRA/issues/JRA-4?filter=allopenissues
         */
    	console.log ("kissa activated");
        //$(document).ready(function(){hideSecurityLevel()});
    	JIRA.bind(JIRA.Events.NEW_CONTENT_ADDED, function (event, $context, reason) { 
            if (reason === JIRA.CONTENT_ADDED_REASON.pageLoad || reason === JIRA.CONTENT_ADDED_REASON.panelRefreshed) {
            	hideSecurityLevel();
            }
            
        });

        hideSecurityLevel();
    });

    /**
     * Hide the clone menu
     */
    function hideSecurityLevel() {
       /* var projectKey = "HIDESEC"; 
        var issue = JIRA.Issue.getIssueKey();
        if (typeof (issue) !== "undefined") {
            var currentProject = issue.substr(0, issue.indexOf('-')); 
            if (currentProject == projectKey) {
            	log('[DEBUG] - SECURITY-LEVEL-HIDE HIT');
                AJS.$('#security-val').parent().parent().hide();
                
            }
        }
        */ 
    	console.log ("kissa xxxxactivated");
    	AJS.$('#security-val').parent().parent().hide();
    }
})(AJS.$);