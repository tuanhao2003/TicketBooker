-- Table: Account
CREATE TABLE Account (
    accountId INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE,
    phone VARCHAR(11) UNIQUE,
    role ENUM('Customer', 'Staff', 'Manager') NOT NULL
);

-- Table: User
CREATE TABLE Users (
    userId INT AUTO_INCREMENT PRIMARY KEY,
    accountId INT,
    firstName VARCHAR(50) NOT NULL,
    lastName VARCHAR(50) NOT NULL,
    address VARCHAR(255),
    dateOfBirth DATE,
    gender ENUM('Male', 'Female', 'Other'),
    profilePhoto VARCHAR(255),
    FOREIGN KEY (accountId) REFERENCES Account(accountId)
);

-- Table: Routes
CREATE TABLE Routes (
    routeId INT AUTO_INCREMENT PRIMARY KEY,
    departureLocation VARCHAR(100) NOT NULL,
    arrivalLocation VARCHAR(100) NOT NULL,
    estimatedTime TIME,
    routeStatus ENUM('Active', 'Inactive') NOT NULL
);

-- Table: Buses
CREATE TABLE Buses (
    busId INT AUTO_INCREMENT PRIMARY KEY,
    routeId INT,
    licensePlate VARCHAR(20) NOT NULL UNIQUE,
    busType ENUM('BedSeat', 'Seat') NOT NULL,
    capacity INT NOT NULL,
    status ENUM('Active', 'Inactive') NOT NULL,
    FOREIGN KEY (routeId) REFERENCES Routes(routeId)
);

-- Table: Driver
CREATE TABLE Driver (
    driverId INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    licenseNumber VARCHAR(20) UNIQUE NOT NULL,
    phone VARCHAR(11) UNIQUE,
    address VARCHAR(255),
    status ENUM('Active', 'Inactive') NOT NULL
);

-- Table: Trips
CREATE TABLE Trips (
    tripId INT AUTO_INCREMENT PRIMARY KEY,
    routeId INT,
    busId INT,
    driverId INT,
    departureStation VARCHAR(100),
    arrivalStation VARCHAR(100),
    departureTime DATETIME NOT NULL,
    arrivalTime DATETIME,
    price INT,
    availableSeats INT,
    tripStatus ENUM('Scheduled', 'Completed', 'Canceled') NOT NULL,
    FOREIGN KEY (routeId) REFERENCES Routes(routeId),
    FOREIGN KEY (busId) REFERENCES Buses(busId),
    FOREIGN KEY (driverId) REFERENCES Driver(driverId)
);
-- Table: Seats
CREATE TABLE Seats (
    seatId INT AUTO_INCREMENT PRIMARY KEY,
    tripId INT,
    seatCode VARCHAR(10) NOT NULL,
    seatStatus ENUM('Sold', 'Available', 'Booking', 'Picking') NOT NULL,
    FOREIGN KEY (tripId) REFERENCES Trips(tripId)
);


-- Table: Invoices
CREATE TABLE Invoices (
    invoiceId INT AUTO_INCREMENT PRIMARY KEY,
    totalAmount INT,
    paymentStatus ENUM('Pending', 'Paid') NOT NULL,
    paymentTime DATETIME,
    paymentMethod ENUM('CreditCard', 'EWallet') NOT NULL
);

-- Table: Tickets
CREATE TABLE Tickets (
    ticketId INT AUTO_INCREMENT PRIMARY KEY,
    tripId INT,
    bookerId INT NULL,
    invoiceId INT,
    customerName VARCHAR(100) NOT NULL,
    customerPhone VARCHAR(15) NOT NULL,
    seatId INT,
    qrCode VARCHAR(255),
    status ENUM('Booked', 'Canceled', 'Used') NOT NULL,
    FOREIGN KEY (tripId) REFERENCES Trips(tripId),
    FOREIGN KEY (bookerId) REFERENCES Account(accountId),
    FOREIGN KEY (invoiceId) REFERENCES Invoices(invoiceId),
    FOREIGN KEY (seatId) REFERENCES Seats(seatId)
);
-- Inserting into Account table
INSERT INTO Account (username, password, email, phone, role) VALUES
('john_doe', 'password123', 'john@example.com', '0123456789', 'Customer'),
('jane_smith', 'securePass!456', 'jane@example.com', '0987654321', 'Staff'),
('manager_mike', 'manager@321', 'mike@example.com', '0911223344', 'Manager'),
('alice_walker', 'alicePass321!', 'alice@example.com', '0911223345', 'Customer'),
('bob_jones', 'bobPass789!', 'bob@example.com', '0933445566', 'Customer');

-- Inserting into Users table
INSERT INTO Users (accountId, firstName, lastName, address, dateOfBirth, gender, profilePhoto) VALUES
(1, 'John', 'Doe', '123 Main St', '1985-04-23', 'Male', 'john_profile.jpg'),
(2, 'Jane', 'Smith', '456 Park Ave', '1990-07-12', 'Female', 'jane_profile.jpg'),
(3, 'Mike', 'Manager', '789 Oak Dr', '1980-10-05', 'Male', 'mike_profile.jpg'),
(4, 'Alice', 'Walker', '135 Maple St', '1995-02-20', 'Female', 'alice_profile.jpg'),
(5, 'Bob', 'Jones', '246 Pine Rd', '1988-08-15', 'Male', 'bob_profile.jpg');

-- Inserting into Routes table
INSERT INTO Routes (departureLocation, arrivalLocation, estimatedTime, routeStatus) VALUES
('City A', 'City B', '02:30:00', 'Active'),
('City B', 'City C', '03:00:00', 'Active'),
('City C', 'City D', '04:00:00', 'Inactive'),
('City A', 'City D', '05:00:00', 'Active'),
('City B', 'City A', '02:45:00', 'Active');

-- Inserting into Buses table
INSERT INTO Buses (routeId, licensePlate, busType, capacity, status) VALUES
(1, 'ABC123', 'BedSeat', 40, 'Active'),
(2, 'XYZ789', 'Seat', 30, 'Active'),
(3, 'LMN456', 'BedSeat', 50, 'Inactive'),
(4, 'JKL321', 'Seat', 35, 'Active'),
(5, 'QWE654', 'BedSeat', 45, 'Active');

-- Inserting into Driver table
INSERT INTO Driver (name, licenseNumber, phone, address, status) VALUES
('John Driver', 'L123456', '0112233445', '789 Elm St', 'Active'),
('Jane Driver', 'L654321', '0223344556', '321 Cedar St', 'Inactive'),
('Mike Driver', 'L987654', '0334455667', '654 Birch St', 'Active'),
('Alice Driver', 'L112233', '0445566778', '951 Walnut St', 'Active'),
('Bob Driver', 'L998877', '0556677889', '852 Chestnut St', 'Active');

