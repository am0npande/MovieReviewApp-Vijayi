# MovieReviewApp-Vijayi

# VijayIProject - Movie App (Jetpack Compose + RxKotlin + MVVM)

A modern Android movie listing app built with **Jetpack Compose**, **RxKotlin**, and **MVVM architecture**.  
This app demonstrates best practices for clean architecture, reactive programming, and UI state management using **StateFlow** and **RxKotlin**.

---

## 🚀 Features

-  Fetch **Popular** and **Upcoming Movies** from API  
-  Perform **parallel API calls** using `Observable.zip()` (RxKotlin)  
-  Use **Repository Pattern** for clean data management  
- **StateFlow** for UI updates  
- **RxKotlin BehaviorSubject** for single-event communication (like movie selection)  
-  Built with **MVVM Architecture**  
-  Modern UI using **Jetpack Compose Material 3**  
-  Gradient-themed color scheme for a premium look  

---

## Tech Stack

| Layer | Technology Used |
|-------|------------------|
| **UI** | Jetpack Compose, Material 3 |
| **State Management** | Kotlin StateFlow, RxKotlin |
| **Architecture** | MVVM + Repository |
| **Reactive Programming** | RxKotlin (Observable.zip, BehaviorSubject) |
| **Networking** | Retrofit + RxJava3 Adapter |
| **Dependency Injection** | Manual (or Koin/Dagger optional) |
| **Language** | Kotlin |

---

## Architecture Overview

Presentation (Compose Screens + ViewModel)
Domain (Repository Interface)
Data (Repository Implementation + DTOs + Retrofit APIs)

Two API calls (`Popular` & `Upcoming`) are executed **in parallel** using:

```kotlin
Observable.zip(
    repository.getMoviesUpComingList(),
    repository.getMoviesPopularList()
) { upcoming, popular -> Pair(upcoming, popular) }
