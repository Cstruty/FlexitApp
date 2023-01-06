# FlexiT
Mohammed, Refath, Nicole, Cypres

Project: Ever wanted an all-in-one workout app, don't look any further. FlexiT is everything your regular work-out app wishes to be. 

Instructions to install app:

Download the app on an android phone via the link: https://drive.google.com/file/d/11rMcqK0PVpGiYi2lt-Af-xdjGpAQF_xH/view?usp=sharing

After the apk is installed on the phone, you can simply run it like a regular app.

## Test Cases

For testing we used JUnit along with android context creation to test the REST API Client and the parser for the response from the JSON.

### DAO Test Case Problem

It is a known problem that the search test cases do not work within the context of the unit test.
However, the feature works within the context of the app its self, which requires more research, however we don't have time for that now

### JSON Parser tester 
For testing JSON parsing, two different types of tests for both the Author parsing and the body parsing. The first test takes a known bad string and throws an expecption. The second set of tests will take a list of known good JSONs, and check the output of those parsings with increasing difficulty. Future tests would also include known security issues, and try to account for those security issues.
