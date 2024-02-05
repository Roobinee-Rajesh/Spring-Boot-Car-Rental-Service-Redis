# **Car Rental Service**


**Description**

 Platform is designed to streamline the car rental process, providing a seamless experience for users, efficient management for administrators, and excellent maintenance service from staff.

 **➡️ User Role**

  **User-friendly Interface**: Interface allows users to easily browse through a diverse fleet of vehicles, complete with detailed descriptions, images, and pricing information. Users can effortlessly select their preferred car
  choose rental dates, and make online reservations.

  **Personalized Profiles**: Users can view personalized profiles to track their reservation history, view or cancel the upcomming reservations and modify their profile.

  **Email Notification**: The user will receive a confirmation email on their reservation upon successful transaction. 

**➡️ Admin Role**

  **Dashboard**: Administrators have access to a comprehensive dashboard that provides real-time insights into reservation activities, car availability and staff maintenance. This helps in making data-driven decisions to enhance the overall service.

  **Car Operation**: Administrators  have the capability to perform CRUD (Create, Read, Update, Delete) operations specifically tailored for managing the fleet of cars available for reservation. The "Create" function allows administrators to effortlessly add new vehicles to the system, ensuring that our users have access to an extensive and diverse range of options. With the "Read" operation, admins can easily view detailed information about each car in the fleet, such as model specifications. The "Update" operation empowers administrators to modify existing car details, keeping information accurate and relevant. Lastly, the "Delete" operation allows for the removal of outdated or retired vehicles, maintaining a streamlined and efficient inventory for our users.

  **Staff Operation**: In addition to managing the fleet of cars, administrators also have control over the staff operations within the system. The CRUD operations for staff encompass various aspects of personnel management. Administrators can "Create" new staff profiles and contact information. The "Read" operation allows them to access and review staff details, ensuring an organized and well-documented staff directory. With the "Update" operation, admins can modify staff information as needed, whether it's a change in contact details or an update to roles and responsibilities. Lastly, the "Delete" operation enables administrators to manage staff turnover, ensuring that the system reflects the current staff roster accurately. This comprehensive set of CRUD operations for both cars and staff empowers administrators to maintain a well-functioning and efficient car reservation service.

  **Maintenance**: Administrators have the capability to manage the maintenance status of each vehicle in the fleet. This involves monitoring and scheduling maintenance of staff to each car and overall inspections. The system provides administrators with a clear overview of each car's maintenance history, allowing for proactive scheduling of upcoming service needs. 

**➡️ Staff Role**
  Staff can check the maintenance that is planned for the month and, when it is finished, change the status.


**Credentials**

👉 User 
   📧Email = "user" | 🔐Password = "user"
👉 Admin 
   📧Email = "admin" | 🔐Password = "admin"
👉 Staff 
   📧Email = "staff" | 🔐Password = "staff"


**Technologies used**

👉 '💻-Spring Boot Java' for Back end
👉 '💻-Angular' for Front end 
👉 '🗃️-MySql' for Database 


**Screenshots**

  **Login**

  ![image](https://github.com/Roobinee-Rajesh/Car-Rental-Service-Angular/assets/145538061/9c6e2e0d-7694-498c-a047-d9ad8bceecdf)

  **Register**
  
   ![image](https://github.com/Roobinee-Rajesh/Car-Rental-Service-Angular/assets/145538061/2fa6b5f3-1fef-4890-9f1e-97bbbd2ca157)

  **User Home**
  
  ![image](https://github.com/Roobinee-Rajesh/Car-Rental-Service-Angular/assets/145538061/3c8f5d1b-300a-4d7f-80fa-95a9e171497b)

  **User Search**

  ![image](https://github.com/Roobinee-Rajesh/Car-Rental-Service-Angular/assets/145538061/b7ec9342-5a38-47f1-8588-8363235a44df)

  **User Booking**

  ![image](https://github.com/Roobinee-Rajesh/Car-Rental-Service-Angular/assets/145538061/913146be-2843-43c0-80dd-bc7e37f4441c)

  **User Confirmation**

  ![image](https://github.com/Roobinee-Rajesh/Car-Rental-Service-Angular/assets/145538061/b5016497-3617-4c0b-b111-626e5ab08ac3)

  **User Profile**

  ![image](https://github.com/Roobinee-Rajesh/Car-Rental-Service-Angular/assets/145538061/d2521a82-3ced-445a-9843-6e0d31320003)

 **User Booking**

 ![image](https://github.com/Roobinee-Rajesh/Car-Rental-Service-Angular/assets/145538061/7d17d823-f648-4749-9f8e-7273659d25ab)


 **Admin Home**
  
  ![image](https://github.com/Roobinee-Rajesh/Car-Rental-Service-Angular/assets/145538061/1a1804fe-54cd-4cf8-bd47-6b0bf918a4ad)

 **Admin Car**
  
  ![image](https://github.com/Roobinee-Rajesh/Car-Rental-Service-Angular/assets/145538061/6a30eb2b-ce68-4dfa-9635-bb0fc50e7b09)

 **Admin Staff**
  
  ![image](https://github.com/Roobinee-Rajesh/Car-Rental-Service-Angular/assets/145538061/58fcfda7-46b2-49a4-9954-d1b993aecfdf)

 **Staff Home**
 
  ![image](https://github.com/Roobinee-Rajesh/Car-Rental-Service-Angular/assets/145538061/7cdd1e43-4f0c-4c5a-89b8-0d0eb2bd3a68)
  

**Challenges**

    🔴 Faced challenges in creating and mapping the enitites.
    🔴 While integrating front end and back end for POST and PUT request.
    🔴 Uploading image to the server and downloading image from the server.
    🔴 Faced challenge for @OnetoOne mapping for duplicate data.
    🔴 Faced challenge in writing nested JPQL query.


**Hardest Errors** 

    🚩 Internal server error for java persisitance exception for date - Solved it by converting the date format.
    🚩 Internal server error hibernate nested exception - Solved it by reworking on the modal mapping.
    🚩 Internal server error for delete nested entities - Solved it by adding cascade type ALL.
    🚩 Altering the entity and updating it.
    🚩 Handling logic for more than 1 row found for one to one mapping.
    🚩 Model variable name mis-match while passing from front-end to back-end through ngForm and model- Solved by fixing the data type and changing the variable name


**Repository Link**

    🔗 Front-End Repo link : https://github.com/Roobinee-Rajesh/Car-Rental-Service-Angular
    🔗 Back-End Repo link: https://github.com/Roobinee-Rajesh/Spring-Boot-Car-Rental-Service


**MySQL Schema**

 ![image](https://github.com/Roobinee-Rajesh/Car-Rental-Service-Angular/assets/145538061/af6af689-4271-4c57-ae49-4dc6ceaac2ee)


**Demo Video**
  
🔗 https://drive.google.com/file/d/1xcmXwnsdin3Qivm_8Kpxmlajdai7BlP6/view?usp=sharing
  
  
  
  
