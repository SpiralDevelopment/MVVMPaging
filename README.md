# MVVMPaging
> Demonstartions of using Paging Library with MVVM architecture. Samples fetches data about popular movies from [API](https://api.themoviedb.org/) and loads it into Grid Layout.

## Screenshots

<p align="center">
  <img src="https://github.com/SpiralDevelopment/MVVMPaging/blob/paging-dagger-hilt/screenshots/sc1.jpg">
  <img src="https://github.com/SpiralDevelopment/MVVMPaging/blob/paging-dagger-hilt/screenshots/sc2.jpg">
</p>

## Variations

This project hosts each sample app in separate repository branches.

### Stable samples

|     Sample     | Description |
| ------------- | ------------- |
| [paging](https://github.com/DataSmoother/MVVMPaging/tree/paging) | The base for the rest of the branches. <br/>Paging Library + Architecture Components + Retrofit + RxJava |
| [paging-dagger](https://github.com/SpiralDevelopment/MVVMPaging/tree/paging-dagger) | Paging Library + Architecture Components + Retrofit + RxJava + Dagger Android |
| [paging-offline](https://github.com/SpiralDevelopment/MVVMPaging/tree/paging-offline) | Paging Library + Architecture Components + Retrofit + RxJava + Dagger Android + Offline-first|
| [paging-dagger-hilt](https://github.com/SpiralDevelopment/MVVMPaging/tree/paging-dagger-hilt) | Paging Library + Architecture Components + Retrofit + RxJava + Dagger Hilt + Offline-first|

## App Architectures

There are 2 types of architecture used in the samples. 

### Online App Architecture

Online app architecture fetches data from API and directly binds it to the UI. Used in [paging](https://github.com/DataSmoother/MVVMPaging/tree/paging) and [paging-dagger](https://github.com/SpiralDevelopment/MVVMPaging/tree/paging-dagger) samples

<p align="center">
  <img src="https://github.com/SpiralDevelopment/MVVMPaging/blob/paging-dagger-hilt/online_app_diagram.png">
</p>


### Offline-First App Architecture

Offline-first app architecture fetches data from API and inserts that data into database, then those data are binded to the UI. Used in [paging-offline](https://github.com/DataSmoother/MVVMPaging/tree/paging-offline) and [paging-dagger-hilt](https://github.com/SpiralDevelopment/MVVMPaging/tree/paging-dagger-hilt) samples

![offline-app](https://github.com/SpiralDevelopment/MVVMPaging/blob/paging-dagger-hilt/offline_app_diagram.png)
