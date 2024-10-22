INSERT INTO Account (username, password, email, role, accountStatus) VALUES
('john_doe', 'password123', 'john@example.com', 'CUSTOMER', 'ACTIVE'),
('jane_doe', 'password456', 'jane@example.com', 'STAFF', 'ACTIVE'),
('manager1', 'manager789', 'manager@example.com', 'MANAGER', 'ACTIVE'),
('customer123', 'passcust123', 'cust123@example.com', 'CUSTOMER', 'INACTIVE'),
('staff456', 'passstaff456', 'staff456@example.com', 'STAFF', 'ACTIVE'),
('customer789', 'passcust789', 'cust789@example.com', 'CUSTOMER', 'ACTIVE'),
('manager2', 'passmanager2', 'manager2@example.com', 'MANAGER', 'ACTIVE'),
('staff789', 'passstaff789', 'staff789@example.com', 'STAFF', 'INACTIVE'),
('john_smith', 'johnsmith123', 'johnsmith@example.com', 'CUSTOMER', 'ACTIVE'),
('jane_smith', 'janesmith456', 'janesmith@example.com', 'STAFF', 'ACTIVE');

INSERT INTO Users (accountId, fullName, phone, address, dateOfBirth, gender, profilePhoto, userStatus) VALUES
(1, 'John Doe', '0123456789', '123 Main St', '1990-01-01', 'MALE', 'john_doe.jpg', 'ACTIVE'),
(2, 'Jane Doe', '0987654321', '456 Maple Ave', '1985-05-12', 'FEMALE', 'jane_doe.jpg', 'ACTIVE'),
(3, 'Manager One', '0912345678', '789 Oak St', '1975-07-22', 'MALE', NULL, 'ACTIVE'),
(4, 'Customer 123', '0934567890', '101 Pine St', '1992-03-30', 'FEMALE', NULL, 'INACTIVE'),
(5, 'Staff 456', '0912345670', '102 Cedar St', '1988-09-17', 'MALE', NULL, 'ACTIVE'),
(6, 'Customer 789', '0934561234', '103 Elm St', '1994-11-11', 'FEMALE', NULL, 'ACTIVE'),
(7, 'Manager Two', '0956781234', '104 Spruce St', '1980-12-25', 'MALE', NULL, 'ACTIVE'),
(8, 'Staff 789', '0923456712', '105 Fir St', '1991-04-05', 'FEMALE', NULL, 'INACTIVE'),
(9, 'John Smith', '0911123456', '106 Birch St', '1993-06-16', 'MALE', NULL, 'ACTIVE'),
(10, 'Jane Smith', '0945612312', '107 Walnut St', '1987-08-09', 'FEMALE', NULL, 'ACTIVE');

INSERT INTO Routes (departureLocation, arrivalLocation, estimatedTime, routeStatus) VALUES
('City A', 'City B', '02:00:00', 'ACTIVE'),
('City B', 'City C', '01:30:00', 'ACTIVE'),
('City A', 'City C', '03:15:00', 'ACTIVE'),
('City D', 'City E', '02:45:00', 'INACTIVE'),
('City F', 'City G', '04:00:00', 'ACTIVE'),
('City H', 'City I', '01:45:00', 'ACTIVE'),
('City J', 'City K', '03:30:00', 'INACTIVE'),
('City L', 'City M', '02:30:00', 'ACTIVE'),
('City N', 'City O', '03:00:00', 'ACTIVE'),
('City P', 'City Q', '01:00:00', 'ACTIVE');

INSERT INTO Buses (routeId, licensePlate, busType, capacity, busStatus) VALUES
(1, 'ABC123', 'BEDSEAT', 40, 'ACTIVE'),
(2, 'DEF456', 'SEAT', 30, 'ACTIVE'),
(3, 'GHI789', 'BEDSEAT', 45, 'ACTIVE'),
(4, 'JKL012', 'SEAT', 35, 'INACTIVE'),
(5, 'MNO345', 'BEDSEAT', 50, 'ACTIVE'),
(6, 'PQR678', 'SEAT', 32, 'ACTIVE'),
(7, 'STU901', 'BEDSEAT', 42, 'INACTIVE'),
(8, 'VWX234', 'SEAT', 28, 'ACTIVE'),
(9, 'YZA567', 'BEDSEAT', 38, 'ACTIVE'),
(10, 'BCD890', 'SEAT', 25, 'ACTIVE');

INSERT INTO Driver (name, licenseNumber, phone, address, driverStatus) VALUES
('Driver One', 'LIC001', '0123456789', '123 Main St', 'ACTIVE'),
('Driver Two', 'LIC002', '0987654321', '456 Maple Ave', 'ACTIVE'),
('Driver Three', 'LIC003', '0912345678', '789 Oak St', 'ACTIVE'),
('Driver Four', 'LIC004', '0934567890', '101 Pine St', 'INACTIVE'),
('Driver Five', 'LIC005', '0912345670', '102 Cedar St', 'ACTIVE'),
('Driver Six', 'LIC006', '0934561234', '103 Elm St', 'ACTIVE'),
('Driver Seven', 'LIC007', '0956781234', '104 Spruce St', 'INACTIVE'),
('Driver Eight', 'LIC008', '0923456712', '105 Fir St', 'ACTIVE'),
('Driver Nine', 'LIC009', '0911123456', '106 Birch St', 'ACTIVE'),
('Driver Ten', 'LIC010', '0945612312', '107 Walnut St', 'ACTIVE');

INSERT INTO Trips (routeId, busId, driverId, departureStation, arrivalStation, departureTime, arrivalTime, price, availableSEATs, tripStatus) VALUES
(1, 1, 1, 'Station A', 'Station B', '2024-10-22 08:00:00', '2024-10-22 10:00:00', 100, 40, 'SCHEDULED'),
(2, 2, 2, 'Station B', 'Station C', '2024-10-22 09:00:00', '2024-10-22 10:30:00', 80, 30, 'SCHEDULED'),
(3, 3, 3, 'Station A', 'Station C', '2024-10-22 11:00:00', '2024-10-22 14:15:00', 120, 45, 'SCHEDULED'),
(4, 4, 4, 'Station D', 'Station E', '2024-10-22 12:00:00', NULL, 90, 35, 'CANCELLED'),
(5, 5, 5, 'Station F', 'Station G', '2024-10-22 13:00:00', '2024-10-22 17:00:00', 150, 50, 'SCHEDULED'),
(6, 6, 6, 'Station H', 'Station I', '2024-10-22 14:00:00', '2024-10-22 15:45:00', 110, 32, 'SCHEDULED'),
(7, 7, 7, 'Station J', 'Station K', '2024-10-22 15:00:00', NULL, 130, 42, 'CANCELLED'),
(8, 8, 8, 'Station L', 'Station M', '2024-10-22 16:00:00', '2024-10-22 18:30:00', 140, 28, 'SCHEDULED'),
(9, 9, 9, 'Station N', 'Station O', '2024-10-22 17:00:00', '2024-10-22 20:00:00', 125, 38, 'SCHEDULED'),
(10, 10, 10, 'Station P', 'Station Q', '2024-10-22 18:00:00', '2024-10-22 19:00:00', 90, 25, 'SCHEDULED');

INSERT INTO Seats (tripId, seatCode, seatStatus) VALUES
(1, 'A1', 'AVAILABLE'),
(1, 'A2', 'SOLD'),
(1, 'A3', 'BOOKING'),
(2, 'B1', 'AVAILABLE'),
(2, 'B2', 'SOLD'),
(2, 'B3', 'BOOKING'),
(3, 'C1', 'AVAILABLE'),
(3, 'C2', 'SOLD'),
(3, 'C3', 'BOOKING'),
(4, 'D1', 'AVAILABLE');

INSERT INTO Invoices (totalAmount, paymentStatus, paymentTime, paymentMethod) VALUES
(100, 'PAID', '2024-10-22 10:15:00', 'CREDITCARD'),
(80, 'PAID', '2024-10-22 10:45:00', 'CREDITCARD'),
(120, 'PAID', '2024-10-22 14:30:00', 'EWALLET'),
(90, 'PENDING', NULL, 'CASH'),
(150, 'PAID', '2024-10-22 17:30:00', 'CREDITCARD'),
(110, 'PAID', '2024-10-22 16:00:00', 'EWALLET'),
(130, 'PENDING', NULL, 'CASH'),
(140, 'PAID', '2024-10-22 19:00:00', 'EWALLET'),
(125, 'PAID', '2024-10-22 20:30:00', 'CREDITCARD'),
(90, 'PAID', '2024-10-22 19:15:00', 'CASH');

INSERT INTO Tickets (tripId, bookerId, invoiceId, customerName, customerPhone, seatId, qrCode, ticketStatus) VALUES
(1, 1, 1, 'John Doe', '0123456789', 1, 'QR001', 'BOOKED'),
(1, 2, 2, 'Jane Doe', '0987654321', 2, 'QR002', 'BOOKED'),
(1, 3, 3, 'Manager One', '0912345678', 3, 'QR003', 'CANCELLED'),
(2, 4, 4, 'Customer 123', '0934567890', 4, 'QR004', 'BOOKED'),
(2, 5, 5, 'Staff 456', '0912345670', 5, 'QR005', 'BOOKED'),
(2, 6, 6, 'Customer 789', '0934561234', 6, 'QR006', 'USED'),
(3, 7, 7, 'Manager Two', '0956781234', 7, 'QR007', 'BOOKED'),
(3, 8, 8, 'Staff 789', '0923456712', 8, 'QR008', 'BOOKED'),
(3, 9, 9, 'John Smith', '0911123456', 9, 'QR009', 'USED'),
(3, 10, 10, 'Jane Smith', '0945612312', 10, 'QR010', 'BOOKED');
