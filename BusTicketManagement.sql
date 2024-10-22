-- Table: Account
CREATE TABLE Account (
    accountId INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE,
    role ENUM('CUSTOMER', 'STAFF', 'MANAGER') NOT NULL,
    accountStatus ENUM('ACTIVE', 'INACTIVE') NOT NULL
);

-- Table: User
CREATE TABLE Users (
    userId INT AUTO_INCREMENT PRIMARY KEY,
    accountId INT,
    fullName VARCHAR(255) NOT NULL,
    phone VARCHAR(11) UNIQUE NOT NULL,
    address VARCHAR(255),
    dateOfBirth DATE,
    gender ENUM('MALE', 'FEMALE', 'OTHER'),
    profilePhoto VARCHAR(255),
    FOREIGN KEY (accountId) REFERENCES Account(accountId),
    userStatus ENUM('ACTIVE', 'INACTIVE') NOT NULL
);

-- Table: Routes
CREATE TABLE Routes (
    routeId INT AUTO_INCREMENT PRIMARY KEY,
    departureLocation VARCHAR(100) NOT NULL,
    arrivalLocation VARCHAR(100) NOT NULL,
    estimatedTime TIME,
    routeStatus ENUM('ACTIVE', 'INACTIVE') NOT NULL
);

-- Table: Buses
CREATE TABLE Buses (
    busId INT AUTO_INCREMENT PRIMARY KEY,
    routeId INT,
    licensePlate VARCHAR(20) NOT NULL UNIQUE,
    busType ENUM('BEDSEAT', 'SEAT') NOT NULL,
    capacity INT NOT NULL,
    busStatus ENUM('ACTIVE', 'INACTIVE') NOT NULL,
    FOREIGN KEY (routeId) REFERENCES Routes(routeId)
);

-- Table: Driver
CREATE TABLE Driver (
    driverId INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    licenseNumber VARCHAR(20) UNIQUE NOT NULL,
    phone VARCHAR(11) UNIQUE,
    address VARCHAR(255),
    driverStatus ENUM('ACTIVE', 'INACTIVE') NOT NULL
);

-- Table: Trips
CREATE TABLE Trips (
    tripId INT AUTO_INCREMENT PRIMARY KEY,
    routeId INT NOT NULL,
    busId INT not null,
    driverId INT not null ,
    departureStation VARCHAR(100) not null,
    arrivalStation VARCHAR(100) not null,
    departureTime DATETIME NOT NULL,
    arrivalTime DATETIME,
    price INT not null,
    availableSeats INT not null,
    tripStatus ENUM('SCHEDULED', 'COMPLETED', 'CANCELLED') NOT NULL,
    FOREIGN KEY (routeId) REFERENCES Routes(routeId),
    FOREIGN KEY (busId) REFERENCES Buses(busId),
    FOREIGN KEY (driverId) REFERENCES Driver(driverId)
);
-- Table: Seats
CREATE TABLE Seats (
    seatId INT AUTO_INCREMENT PRIMARY KEY,
    tripId INT NOT NULL,
    seatCode VARCHAR(10) NOT NULL,
    seatStatus ENUM('SOLD', 'AVAILABLE', 'BOOKING') NOT NULL,
    FOREIGN KEY (tripId) REFERENCES Trips(tripId)
);


-- Table: Invoices
CREATE TABLE Invoices (
    invoiceId INT AUTO_INCREMENT PRIMARY KEY,
    totalAmount INT,
    paymentStatus ENUM('PENDING', 'PAID') NOT NULL,
    paymentTime DATETIME,
    paymentMethod ENUM('CREDITCARD', 'EWALLET', 'CASH') NOT NULL
);

-- Table: Tickets
CREATE TABLE Tickets (
    ticketId INT AUTO_INCREMENT PRIMARY KEY,
    tripId INT NOT NULL,
    bookerId INT NOT NULL,
    invoiceId INT,
    customerName VARCHAR(100) NOT NULL,
    customerPhone VARCHAR(15) NOT NULL,
    seatId INT NOT NULL,
    qrCode VARCHAR(255),
    ticketStatus ENUM('BOOKED', 'CANCELLED', 'USED') NOT NULL,
    FOREIGN KEY (tripId) REFERENCES Trips(tripId),
    FOREIGN KEY (bookerId) REFERENCES Account(accountId),
    FOREIGN KEY (invoiceId) REFERENCES Invoices(invoiceId),
    FOREIGN KEY (seatId) REFERENCES Seats(seatId)
);
