REM Local DEV
mysql --host=localhost --user=root --password=root < script-sequence.txt

REM QA
REM mysql --host=covid-mysql-1.cxrsplsyuazy.us-east-2.rds.amazonaws.com --user=admin --password=FireDrill$123 < script-sequence.txt

REM Load Test India
REM mysql --host=covid-test-db-2.czlsfzm6jget.ap-south-1.rds.amazonaws.com --user=admin --password=FireDrillTest$123 < script-sequence.txt

REM Live Production India
REM mysql --host=covid-live-production-db-1.cebxny1mwjdi.ap-south-1.rds.amazonaws.com --user=admin --password=CovidAppProductionDatabase$123 < script-sequence.txt