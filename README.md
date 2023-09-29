# mfframework

to use this in other project add the mavenrepository with this artifacts to your ~/.m2/settings.xml. 
in my case it ist my local nexus where I deploy all my artifacts.

```xml
<mirror>
  <id>my-local-repo</id>
  <mirrorOf>external:http:*</mirrorOf>
  <url>http://www.ebi.ac.uk/intact/maven/nexus/content/repositories/ebi-repo/</url>
  <blocked>false</blocked>
</mirror>
```

## get started ##

start with repo mfinfra first

to install the application manually with helm install the complete bundle see repo mfbundle

if Database is fresh: add via gui or api-Docs: currency EUR and USD
then add equities for example DE0005140008 deutsche Bank
US5949181045 Microsoft
add symbols MSFT, DB
start import prices

### setup keycloak ###

for each environment you have to setup keycloak initialy. 
To do so you have to go to the admin-page and create the Realm myfinance
Create all Users and passwords
realm settings - sessions: set SSO Session idle to 5h
realm settings - tokens: set access token lifespan to 4h
Export config: kubectl exec -n mfdev --stdin kubectl exec -n mfdev --stdin --tty pod/keycloak-55c6f45f7d-7mtvt -- /bin/bash
/opt/keycloak/bin/kc.sh export --dir /opt/keycloak/data/import --realm myfinance --users realm_file

create client: mfclient (set valid redirecturl: http://localhost:4200/* and weborigins http://localhost:4200)

## development ##

### SNAPSHOTS ###

We only use maven snapshot-versions for the local build not for the CI-Build, because each build generates a docker image and a helm chart.
Both do not support Snapshots but have to distinguish the builds. So each CI-build needs a different number. 
We use the commit_id for that. This brings the additional advantage of a strong binding from the artifact to the code. 
But we loose the main advantage of Snapshots with that: not changing dependency-versions during development. 
For that the local SNAPSHOT build helps if one develeper changes two dependent components. 
If the dependency is developed by another developer, you will most of the time better develop with a stabel version and not a moving target.



### format ###

the project uses semantic versioning: https://semver.org/

local build with default version 0.0.0-0-SNAPSHOT
via CI: Major, Minor-version and Patch-Version is defined in version.txt. The pre-release info seperated with"-" and contains the CommitId
EACH CI build replaced the maven version number with help of the version plugin.
major.minor.patch-alpha.CommitID.
Release build has the same process but without pre-release version.
e.g. major.minor.patch

### Branch strategy ###

We allways work on feature branches and merge them to the development-branch at the end, so that you can find at the dev-branch only completed features. 
If you want to make a release you have to merge dev to master. So you can allways see which feature was developed in which release, what is in dev and what in test. 
As soon as the prod rollout was sucessfull a tag should be created in Master.

To keep it simple the CI-Build runs only on the Dev-Branch. That means that you are able to deploy only from this branch.
This is basicly  the git flow workflow but without release branch.
That means for hotfixes in Prod, that you have to deploy all developed features from the last release on.
As long as this software is just for fun, and I'm the only developer and tester this will not be an issue. 
If I test anything I'll do it allready in the featurebranch before I'll merge it to dev.
If this changes we have to edit the filter in the tekton event-lister and parse the revision or 
even better create an additional webhook, eventlister and pipeline to deploy the releasebranch in a different environment.


create dev branch:
git checkout -b dev
create feature branch
git checkout -b a_feature (to switch back: git checkout master)
you are on branch a_feature yet, see git branch
use MYF_[tasknumber]_description for the featurename
git commit -am "a description"
git push origin featurebranchname

merge to dev:
do that via github frontend and create and merge a pullrequest
delete featurebranch: git branch -d  featurename


make a release:
git checkout dev
//change and commit the version in the version.txt to major_minor_micro - do it in the dev branch to avoid mergeconflicts
git push origin dev
git checkout master
git merge dev
git tag -a Release_major_minor_micro -m "this is the latest commit"
git push origin master --follow-tags
//after sucessful rollout

prepare for next release:
git checkout dev
//change and commit the version in the jenkins-file to major_minor_micro-alpha.
commit and push

for the deployment you have to change the app version in the helm-chart of the mfdeployment repository and merge the dev branch to the prod-branch

- on feature branches and on the master will be no ci-builds

git log --oneline --graph --decorate

### Backend access ###


#### local development ####

to create the envirnment on your local maschine install kubernetes (Docker desktop, minikube etc)
Then run kubectl apply -f .\devenv_deploy.yaml
install Studio 3T Free to query the mongodb

#### development with gitpod ####

for the development of the frontend with the gitpod ide it is necessary to have a dev backend available. For this the backend will publish via ci after every commit at my server https://babcom.myds.me:30022/dac/rest.
SSL usage is important or other wise no connection is allowed from an gitpod envirmonment.
to create a certificate I've used my synology:
- control_center-external_access-ddns add babcom.myds.me
- control_center-security-certificate add new lets encrypt certifikate
- control_center-security-certificate export certificate
  the is easier but you can use lets encrypt directly or any other service to create a certificate as well

to use the certificate in the backend you have to do the following steps:
- unzip at your win-client and upload them to a linix server with java installed (currently my devenv2 server see MYF-527)
- rename privkey.pem to privkey.key
- openssl pkcs12 -export -out eneCert.pkcs12 -inkey privkey.pem -in cert.pem
- keytool -genkey -keyalg RSA -alias selfsigned -keystore devkeystore.jks  //use your personal infos but mind to use the same password as configured in in dac.res org.ops4j.pax.web.ssl.password
- keytool -delete -alias selfsigned -keystore devkeystore.jks //delete default certifikate
- keytool -v -importkeystore -srckeystore eneCert.pkcs12 -srcstoretype PKCS12 -destkeystore devkeystore.jks -deststoretype JKS
- copy the certificate to /mnt/data/mf/dev_config and restart the container.
- add a portforwarding to the backend ssl port 30022

It is the same for Prod but with another port(for me 30042).
I use a reversproxy for frontend. In this case the user do not have to use a special port and you don't have to add a ssl certificate to your frontend - just handle this in your reverse proxy.
I would like to do it the same way for the backend but unfortunately my integrated Reversproxy in the firewall is only working for the root domain.
You have to publish the backend with https as well because an https frontend is not allowed to communicate with an unsecure backend.

##APIdoc
see http://yourserviceurl/openapi/swagger-ui.html


#test api request with oauth2

request the token:
curl \
-d "client_id=mfclient" \
-d "username=user" \
-d "password=pw" \
-d "grant_type=password" \
"http://localhost:30024/realms/myfinance/protocol/openid-connect/token"

you get something like this:
{"access_token":"eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJidG1qb0NaY3RoNTdBS0lBdkY5bXRqUl9fdFVBSERCX0tqYmY0aW5QczlRIn0.eyJleHAiOjE2OTI0MzIxNzgsImlhdCI6MTY5MjQzMDM3OCwianRpIjoiNTBjY2E1NDUtMjcwNC00NzljLTkxOGEtNDQwYTIzNDBiOTRiIiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDozMDAyNC9yZWFsbXMvbXlmaW5hbmNlIiwiYXVkIjoiYWNjb3VudCIsInN1YiI6IjY3MmE5Y2Y3LTNkZjUtNGE2My04OGU3LWM0MjRiNjAwZjQ0MCIsInR5cCI6IkJlYXJlciIsImF6cCI6Im1mY2xpZW50Iiwic2Vzc2lvbl9zdGF0ZSI6ImI4ZDUzNWUxLTQxYmItNDYwNy1iMjFiLTAzOGUzYzIwYjdiZCIsImFjciI6IjEiLCJhbGxvd2VkLW9yaWdpbnMiOlsiaHR0cDovL2xvY2FsaG9zdDo0MjAwIl0sInJlYWxtX2FjY2VzcyI6eyJyb2xlcyI6WyJkZWZhdWx0LXJvbGVzLW15ZmluYW5jZSIsIm9mZmxpbmVfYWNjZXNzIiwidW1hX2F1dGhvcml6YXRpb24iXX0sInJlc291cmNlX2FjY2VzcyI6eyJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6ImVtYWlsIHByb2ZpbGUiLCJzaWQiOiJiOGQ1MzVlMS00MWJiLTQ2MDctYjIxYi0wMzhlM2MyMGI3YmQiLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsIm5hbWUiOiJIb2xnZXIgRklTQ0hFUiIsInByZWZlcnJlZF91c2VybmFtZSI6ImhvbGdlciIsImdpdmVuX25hbWUiOiJIb2xnZXIiLCJmYW1pbHlfbmFtZSI6IkZJU0NIRVIiLCJlbWFpbCI6ImhvbGdlckBoZmlzY2hlci5vcmcifQ.hnF0WCTwxQPq0gqgDy_y_eDn9Nr4m061dNkRjrlTUdAviNxKzvjhmtIz-M5Knd8ahEPKCMCQPr0dM7lyd2suleCFM8KZRK0d-mc_NwP9yhf8VsO3Ti2D0yclVt8qpUv7qnKaOHDDdh9k632p1TXKY7oEh19VlgbMDp7lPUTJjcsY8kFONl69PVf_ayTDcvzTOFOpCWTbPaOjxunh6QpxgWEqifpixgVoqbBLai5ibgsWCy6COQw2wa0iOxX9YYq70MXAkrK5EETGzRyiCdA4cK4aGBLLPWOkLER9CzizTUSfDixq_eg96tklJq_gHSizP7uobKWh2oWc6nWi9qlEKA","expires_in":1800,"refresh_expires_in":1800,"refresh_token":"eyJhbGciOiJIUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICI0Y2IzNDk3My03NzQwLTRhZGEtODY1ZS1jYTY4NmQ1ODJjNTkifQ.eyJleHAiOjE2OTI0MzIxNzgsImlhdCI6MTY5MjQzMDM3OCwianRpIjoiOTNlODQyZGMtZmUwNy00ZDEwLWE2NmEtNDA1NWUxYzVjNzQwIiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDozMDAyNC9yZWFsbXMvbXlmaW5hbmNlIiwiYXVkIjoiaHR0cDovL2xvY2FsaG9zdDozMDAyNC9yZWFsbXMvbXlmaW5hbmNlIiwic3ViIjoiNjcyYTljZjctM2RmNS00YTYzLTg4ZTctYzQyNGI2MDBmNDQwIiwidHlwIjoiUmVmcmVzaCIsImF6cCI6Im1mY2xpZW50Iiwic2Vzc2lvbl9zdGF0ZSI6ImI4ZDUzNWUxLTQxYmItNDYwNy1iMjFiLTAzOGUzYzIwYjdiZCIsInNjb3BlIjoiZW1haWwgcHJvZmlsZSIsInNpZCI6ImI4ZDUzNWUxLTQxYmItNDYwNy1iMjFiLTAzOGUzYzIwYjdiZCJ9.J_kmVLfkv3ifx2w4YN_SW3Z5z0SSVg3I1K0Ygnkg_Sc","token_type":"Bearer","not-before-policy":0,"session_state":"b8d535e1-41bb-4607-b21b-038e3c20b7bd","scope":"email profile"}%     
you only need the token:
eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJidG1qb0NaY3RoNTdBS0lBdkY5bXRqUl9fdFVBSERCX0tqYmY0aW5QczlRIn0.eyJleHAiOjE2OTI0MzIxNzgsImlhdCI6MTY5MjQzMDM3OCwianRpIjoiNTBjY2E1NDUtMjcwNC00NzljLTkxOGEtNDQwYTIzNDBiOTRiIiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDozMDAyNC9yZWFsbXMvbXlmaW5hbmNlIiwiYXVkIjoiYWNjb3VudCIsInN1YiI6IjY3MmE5Y2Y3LTNkZjUtNGE2My04OGU3LWM0MjRiNjAwZjQ0MCIsInR5cCI6IkJlYXJlciIsImF6cCI6Im1mY2xpZW50Iiwic2Vzc2lvbl9zdGF0ZSI6ImI4ZDUzNWUxLTQxYmItNDYwNy1iMjFiLTAzOGUzYzIwYjdiZCIsImFjciI6IjEiLCJhbGxvd2VkLW9yaWdpbnMiOlsiaHR0cDovL2xvY2FsaG9zdDo0MjAwIl0sInJlYWxtX2FjY2VzcyI6eyJyb2xlcyI6WyJkZWZhdWx0LXJvbGVzLW15ZmluYW5jZSIsIm9mZmxpbmVfYWNjZXNzIiwidW1hX2F1dGhvcml6YXRpb24iXX0sInJlc291cmNlX2FjY2VzcyI6eyJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6ImVtYWlsIHByb2ZpbGUiLCJzaWQiOiJiOGQ1MzVlMS00MWJiLTQ2MDctYjIxYi0wMzhlM2MyMGI3YmQiLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsIm5hbWUiOiJIb2xnZXIgRklTQ0hFUiIsInByZWZlcnJlZF91c2VybmFtZSI6ImhvbGdlciIsImdpdmVuX25hbWUiOiJIb2xnZXIiLCJmYW1pbHlfbmFtZSI6IkZJU0NIRVIiLCJlbWFpbCI6ImhvbGdlckBoZmlzY2hlci5vcmcifQ.hnF0WCTwxQPq0gqgDy_y_eDn9Nr4m061dNkRjrlTUdAviNxKzvjhmtIz-M5Knd8ahEPKCMCQPr0dM7lyd2suleCFM8KZRK0d-mc_NwP9yhf8VsO3Ti2D0yclVt8qpUv7qnKaOHDDdh9k632p1TXKY7oEh19VlgbMDp7lPUTJjcsY8kFONl69PVf_ayTDcvzTOFOpCWTbPaOjxunh6QpxgWEqifpixgVoqbBLai5ibgsWCy6COQw2wa0iOxX9YYq70MXAkrK5EETGzRyiCdA4cK4aGBLLPWOkLER9CzizTUSfDixq_eg96tklJq_gHSizP7uobKWh2oWc6nWi9qlEKA

put the token in the api request
curl -v -H "Origin: http://localhost:4200" -H "Access-Control-Allow-Origin: /" -H "Authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJidG1qb0NaY3RoNTdBS0lBdkY5bXRqUl9fdFVBSERCX0tqYmY0aW5QczlRIn0.eyJleHAiOjE2OTIzODAyMjEsImlhdCI6MTY5MjM3ODQyMSwianRpIjoiODk3ZGFjNGMtNDI2Mi00YTg2LThhYzEtYmRkMjY5ZmZlYTE2IiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDozMDAyNC9yZWFsbXMvbXlmaW5hbmNlIiwiYXVkIjoiYWNjb3VudCIsInN1YiI6IjY3MmE5Y2Y3LTNkZjUtNGE2My04OGU3LWM0MjRiNjAwZjQ0MCIsInR5cCI6IkJlYXJlciIsImF6cCI6Im1mY2xpZW50Iiwic2Vzc2lvbl9zdGF0ZSI6ImY1MWJkMDFiLTgzOTQtNGE3Mi1hZjY2LWU3MjFmOTdkODY3NSIsImFjciI6IjEiLCJhbGxvd2VkLW9yaWdpbnMiOlsiaHR0cDovL2xvY2FsaG9zdDo0MjAwIl0sInJlYWxtX2FjY2VzcyI6eyJyb2xlcyI6WyJkZWZhdWx0LXJvbGVzLW15ZmluYW5jZSIsIm9mZmxpbmVfYWNjZXNzIiwidW1hX2F1dGhvcml6YXRpb24iXX0sInJlc291cmNlX2FjY2VzcyI6eyJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6ImVtYWlsIHByb2ZpbGUiLCJzaWQiOiJmNTFiZDAxYi04Mzk0LTRhNzItYWY2Ni1lNzIxZjk3ZDg2NzUiLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsIm5hbWUiOiJIb2xnZXIgRklTQ0hFUiIsInByZWZlcnJlZF91c2VybmFtZSI6ImhvbGdlciIsImdpdmVuX25hbWUiOiJIb2xnZXIiLCJmYW1pbHlfbmFtZSI6IkZJU0NIRVIiLCJlbWFpbCI6ImhvbGdlckBoZmlzY2hlci5vcmcifQ.kIuxv7dHAR0Fozb7lFSGA_8Y1yIhAH0-NJQhVt5ljFM7H-xE4hBnKFforAtjfUksAcT6dq-wm8L6_CNTKjvO3PYqv-Hh7B8NVtoNWoOSNVqAg_nGF4uJsWlawYhz-KZ7ItADgR2uu8IpP8aV9b9KwZl8c2s_kE5A8vn0I06l_DHytKEpt0tOS9XZmLA8jTiZc-21Y5dtD1sgJonKXjxPIz7rhYidpsZBmUh3znqEF8aMMR6-7LMDhQQn8acbzpQcyh_2MNP01R1JtIGwcNbxoftW7rZ6rn-zl6UyL-8FhDlOZXV3yvblIl28t9TvXOzM10Lhg1boy2ZGA-1e1YoKlw" http://localhost:7009/mf/tenants

