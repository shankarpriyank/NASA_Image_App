# NASA_Image_App

The Nasa Images App is an Android application that provides a gallery of captivating photos published by NASA. The images are retrieved by consuming a simple REST API and are displayed directly on your mobile device.

Key aspects of this app include:

- **Use of Jetpack Compose**: The app takes advantage of the powerful UI toolkit Jetpack Compose to create an aesthetically pleasing and user-friendly design.
- **Model-View-ViewModel (MVVM) architecture**: By adhering to the MVVM pattern, the app ensures a clear separation of data and logic from the interface, enhancing testability and maintainability.
- **Retrofit for network calls**: The app relies on the Retrofit library to handle HTTP requests to NASA's REST API, thereby facilitating seamless network communication.
- **Hilt for dependency injection**: Hilt, a robust dependency injection library, is utilised to effectively manage dependencies within the app.
- **Room for data caching**: Room Persistence Library is employed as an abstraction layer over SQLite to enable more robust database access, ensuring that images can be cached and viewed offline.
- **Coroutines and Flows for asynchronous tasks**: By making use of coroutines and flows, the app handles asynchronous tasks efficiently, improving overall performance and user experience.
- **Navigation Components**: Navigation between different screens in the app is eloquently handled by the Android Navigation component, providing a consistent and predictable user experience.

# Images and Videos

| Image 1   | Image 2   |
|-----------|-----------|
| <img src = "https://github.com/shankarpriyank/NASA_Image_App/assets/75121767/b69c7cde-0627-4b59-a597-e3d7620197eb" height = "400" width= "400"> | <img src = "https://github.com/shankarpriyank/NASA_Image_App/assets/75121767/ecf37956-3de3-4e19-862d-84d08248b282" height = "400" width= "400"> | 


https://github.com/shankarpriyank/NASA_Image_App/assets/75121767/f3b2afdb-a4d5-4018-8152-4850afa2ac4f


