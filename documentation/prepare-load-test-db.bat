@echo off

REM Local DEV
REM mysql --host=localhost --user=root --password=root < generate-load-test-users.sql

REM QA
REM mysql --host=covid-mysql-1.cxrsplsyuazy.us-east-2.rds.amazonaws.com --user=admin --password=FireDrill$123 < generate-load-test-users.sql

REM Load Test India
mysql --host=covid-test-db-2.czlsfzm6jget.ap-south-1.rds.amazonaws.com --user=admin --password=FireDrillTest$123 < generate-load-test-users.sql

REM Live Production India
REM mysql --host=covid-live-production-db-1.cebxny1mwjdi.ap-south-1.rds.amazonaws.com --user=admin --password=CovidAppProductionDatabase$123 < generate-load-test-users.sql