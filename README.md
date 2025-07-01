# 🐾 Veterinary Management System

Bu proje, veteriner kliniklerinde müşteri, hayvan, veteriner hekimi, randevu ve aşı işlemlerinin yönetimini sağlayan bir **Java Spring Boot** uygulamasıdır.

## 🔧 Kullanılan Teknolojiler

- Java 21
- Spring Boot
  - Spring Web
  - Spring Data JPA
- PostgreSQL
- Lombok
- Postman
- Maven

## 🗂️ Proje Yapısı

- `Customer`: Hayvan sahiplerinin bilgileri
- `Animal`: Müşterilere ait hayvanlar
- `Doctor`: Veteriner hekimler
- `AvailableDate`: Hekimlerin uygun oldukları günler
- `Appointment`: Hayvan-hasta için randevu oluşturma sistemi
- `Vaccine`: Hayvanlara uygulanan aşıların takibi

## 📮 API Kullanımı

Tüm endpoint'ler `http://localhost:8080/api` altında sunulmaktadır.  
👉 [Postman koleksiyonu](https://github.com/eneszeybek/veterinary-management-system/blob/main/eneszeybek_vetapp_postman_collection.json) dosyasını kullanarak test edebilirsiniz.

### Örnek Endpoint'ler

- `POST /api/customers`
- `GET /api/animals/by-customer/{customerId}`
- `POST /api/vaccines/{animalId}`
- `GET /api/vaccines/protection-range?start=2025-12-01&end=2026-01-31`
- `POST /api/appointments/{doctorId}/{animalId}`

## 📊 UML Diyagramı

Aşağıda sistemin genel UML diyagramı yer almaktadır:

![UML Diagram](./uml_diagram.png)

## 📁 Ek Dosyalar

- `/uml_diagram.png`: UML sınıf diyagramı
- `/eneszeybek_vetapp_postman_collection.json`: Postman API koleksiyonu
- `/vetdb_backup.sql`: PostgreSQL yedek dosyası

## 👩‍💻 Geliştirici

- Enes Zeybek – [GitHub](https://github.com/eneszeybek)
