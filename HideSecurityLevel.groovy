// Script Runner used to hide Security Level all but admin role people
// Original idea: https://community.atlassian.com/t5/Answers-Developer-Questions/Hide-Security-Level-Field/qaq-p/467654
// 20.3.2018 mika.nokka1@gmail.com 
//
// Script Runner Behaviours configuration
//
// For field: Security Level (see configurations)
// Server side script: 
// Validator Class: com/onresolve/jira/groovy/doit/HideSecurityLevel.groovy
// Validator method: Doit

package com.onresolve.jira.groovy.doit  // this script must be living under this tree starting from /scripts directory (<jira place>/scripts/com/resolve....)
import org.apache.log4j.Logger
import org.apache.log4j.Level
import com.atlassian.jira.component.ComponentAccessor
import com.atlassian.jira.ComponentManager
import com.atlassian.jira.issue.fields.CustomField
import com.atlassian.jira.issue.IssueManager
import com.atlassian.jira.issue.Issue

import com.atlassian.sal.api.user.UserManager
import com.atlassian.jira.issue.security.IssueSecurityLevelManager
import com.atlassian.jira.issue.security.IssueSecuritySchemeManager


import com.onresolve.jira.groovy.user.FieldBehaviours   // class to be used if script in server
public class HideSecurityLevel extends FieldBehaviours {


void Doit() {

// CONFIGURATIONS
def SECURITY="security"  // this drop down menu chooses whether date pickers are shown or not
// END OF CONFIGURATIONS



// set logging to Jira log
def log = Logger.getLogger("SecurityLevelHider") // change for customer system
log.setLevel(Level.DEBUG)  // DEBUG INFO
log.info("---FieldHider started -----------------------------------------------------")

def issueSecuritySchemeManager = ComponentAccessor.getComponent(IssueSecuritySchemeManager)
def issueSecurityLevelManager = ComponentAccessor.getComponent(IssueSecurityLevelManager)

def schemeFromName = issueSecuritySchemeManager.getSchemeObjects().find { it.name == "NB 1394 Design Management Issue Security Scheme" }
def schemeFromProject = issueSecuritySchemeManager.getSchemeFor(ComponentAccessor.projectManager.getProjectByCurrentKey("HIDESEC"))

def securityLvl1 = issueSecurityLevelManager.getIssueSecurityLevels(schemeFromName.id).find { it ->
	it.name == "Normal users"
}?.id
def securityLvl12 = issueSecurityLevelManager.getIssueSecurityLevels(schemeFromName.id).find { it ->
	it.name == "NB1394 Network DD write "
}?.id


log.info("securityLvl1: '${securityLvl1}'")  // 10301 
log.info("securityLvl12: '${securityLvl12}'")  // 10300

//def level = underlyingIssue.getSecurityLevelId()
//log.info("level: '${level}'")

def issuekey=underlyingIssue // from ScriptRunner example code



securityField = getFieldByName(SECURITY)


selection=securityField.getValue() as String // use this Behaviours way

log.info("Issue: '${issuekey}'")
log.info("securityField: '${selection}'")

//id=underlyingIssue.securityLevel.getId()
//log.info("securityField: '${id}'")

//def userUtil = ComponentAccessor.getUserUtil()
//log.debug("Security Value:" + securityField.getValue())
def currentUser = ComponentAccessor.getJiraAuthenticationContext().getUser()
//if (!(userUtil.getGroupNamesForUser(currentUser.name).contains("security"))) {
//log.debug("User " + currentUser + " in security group")
securityField.setHidden(true)
securityField.setRequired(false)
//}
//else {
//log.debug("User " + currentUser + " not in security group")
//securityField.setHidden(false)
//}






log.info("---SecurityLevelHider ending -----------------------------------------------------")
}
}