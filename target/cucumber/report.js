$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("HappyFlowGetRelation.feature");
formatter.feature({
  "line": 1,
  "name": "Get Relation",
  "description": "",
  "id": "get-relation",
  "keyword": "Feature"
});
formatter.before({
  "duration": 857046979,
  "status": "passed"
});
formatter.scenario({
  "line": 4,
  "name": "Try it out",
  "description": "",
  "id": "get-relation;try-it-out",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 3,
      "name": "@Wiremock"
    }
  ]
});
formatter.step({
  "line": 5,
  "name": "I use Httpclient and verify the response",
  "keyword": "Given "
});
formatter.step({
  "line": 6,
  "name": "I use restassured and verify the response",
  "keyword": "Then "
});
formatter.match({
  "location": "ApacheAndRestAssuredSteps.httpClientPoc()"
});
formatter.result({
  "duration": 397768441,
  "status": "passed"
});
formatter.match({
  "location": "ApacheAndRestAssuredSteps.restAssuredPoc()"
});
formatter.result({
  "duration": 1375223353,
  "status": "passed"
});
formatter.after({
  "duration": 15242485,
  "status": "passed"
});
});