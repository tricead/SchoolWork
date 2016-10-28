CREATE TABLE Physician (
  EmployeeID INTEGER PRIMARY KEY NOT NULL,
  Name TEXT NOT NULL,
  Position TEXT NOT NULL,
  SSN INTEGER NOT NULL
); 


CREATE TABLE Patient (
  SSN INTEGER PRIMARY KEY NOT NULL,
  Name TEXT NOT NULL,
  Address TEXT NOT NULL,
  Phone TEXT NOT NULL,
  InsuranceID INTEGER NOT NULL,
  PCP INTEGER NOT NULL
CONSTRAINT fk_Physician_EmployeeID REFERENCES Physician(EmployeeID)
);


CREATE TABLE Appointment (
  AppointmentID INTEGER PRIMARY KEY NOT NULL,
  Patient INTEGER NOT NULL
    CONSTRAINT fk_Patient_SSN REFERENCES Patient(SSN),
  PrepNurse INTEGER
    CONSTRAINT fk_Nurse_EmployeeID REFERENCES Nurse(EmployeeID),
  Physician INTEGER NOT NULL
    CONSTRAINT fk_Physician_EmployeeID REFERENCES Physician(EmployeeID),
  Start DATETIME NOT NULL,
  End DATETIME NOT NULL,
  ExaminationRoom TEXT NOT NULL
);


CREATE TABLE Medication (
  Code INTEGER PRIMARY KEY NOT NULL,
  Name TEXT NOT NULL,
  Brand TEXT NOT NULL,
  Description TEXT NOT NULL
);


CREATE TABLE Prescribes (
  Physician INTEGER NOT NULL
    CONSTRAINT fk_Physician_EmployeeID REFERENCES Physician(EmployeeID),
  Patient INTEGER NOT NULL
    CONSTRAINT fk_Patient_SSN REFERENCES Patient(SSN),
  Medication INTEGER NOT NULL
    CONSTRAINT fk_Medication_Code REFERENCES Medication(Code),
  Date DATETIME NOT NULL,
  Appointment INTEGER
    CONSTRAINT fk_Appointment_AppointmentID REFERENCES Appointment(AppointmentID),
  Dose TEXT NOT NULL,
  PRIMARY KEY(Physician, Patient, Medication, Date)
);
CREATE TABLE Room (
  Number INTEGER PRIMARY KEY NOT NULL,
  Type TEXT NOT NULL,
  Unavailable BOOLEAN NOT NULL
);


CREATE TABLE Stay (
  StayID INTEGER PRIMARY KEY NOT NULL,
  Patient INTEGER NOT NULL
    CONSTRAINT fk_Patient_SSN REFERENCES Patient(SSN),
  Room INTEGER NOT NULL
    CONSTRAINT fk_Room_Number REFERENCES Room(Number),
  Start DATETIME NOT NULL,
  End DATETIME NOT NULL
);


