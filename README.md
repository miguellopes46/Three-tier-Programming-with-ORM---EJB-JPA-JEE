# Three-tier-Programming-with-ORM---EJB-JPA-JEE

Done by:

Miguel Lopes

Miguel Pimenta, uc2018287956@student.uc.pt

Project (for evaluation) ---- 


Grade of 77% on this Assignment----
17,18,19 aren't done.


Objectives
• Gain familiarity with the development of three-tier enterprise applications using
the Java Enterprise Edition (Java/Jakarta EE) model.
• This includes the development of applications based on Enterprise JavaBeans
(EJB),
• The Java Persistence API (JPA) and the Java Persistence Query Language
(JPQL).
• Learn to use logging.


Overview
In this project, students will develop a web application to manage a bus company. The
set of operations of this application is essentially limited to the purchase of tickets.
Next, we go through the requirements of this application. Feel free to add some more
functionality that you may need:
Requirements
1. As an unregistered user, I want to create an account, and insert my personal
information, including email.
2. To create manager accounts the system should use a script, e.g., written in JPA.
3. As an unauthenticated user, I must only have access to the login/register screen.
4. As a user, I want to authenticate and start a session with my e-mail and password.
5. As a user, I want to logout from any location or screen.
6. As a user, I want to edit my personal information.
7. As a user, I want to delete my account, thus erasing all traces of my existence from
the system, including my available items.
8. As a user, I want to list the available trips, providing date intervals.
9. As a user, I want to charge my wallet to be able to purchase tickets. (You may
ignore the step that involves the payment itself)
10. As a user, I want to purchase a ticket. I should be able to select the place.
11. As a user, I may be able to return a ticket for future trips and get a reimbursement
in my wallet.
12. As a user, I can list my trips.
13. As a company manager, I want to create future bus trips, including the departure
date and hour, departure point, destination, capacity, and price. You may assume
that departure and destination points are limited.
14. As a company manager, I want to delete future bus trips. The money of all tickets
should be returned to the correct wallets, and the system should warn the
affected users by email.
15. As a company manager I want to list the passengers that have made more trips
(e.g., the top 5).
16. As a company manager I want to search for all bus trips sorted by date between
two date limits.
17. As a company manager I want to search for all bus trips occurring on a given date.
18. As a company manager I want to list all passengers on a given trip listed during
one of the previous searches.
19. The system sends a daily summary of the revenues of that day’s trips to the
managers.


Additional Remarks
This application should have three distinct layers:
6
• A data layer, working atop a database, to keep all the information. This layer
should expose a CRUD (Create, Read, Update, Delete) functionality using EJBs.
Internally, it should use Java Persistence API. In this assignment, the result
of the queries must be decided in this layer, i.e., the sorting order, what
items to show etc. You should not resort to JavaScript for this purpose.
• A business layer that interacts with the data layer, built with EJBs. Since the
assignment is small, it is acceptable if the business layer uses entities and the
separation of the two layers is not complete.
• A presentation layer developed in some Java-based technology, like JSFs or
JSPs. Data between the presentation layer and the business layer should be
transferred using Data Transfer Objects or other techniques (like sending
a simple identifier). Students should not let the entities travel all the way to
the presentation layer. For simplicity students might transfer entities between
the data and the business layer.
