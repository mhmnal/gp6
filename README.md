Android App — Firebase Auth & Data

An Android application written in Java, backed by Firebase for authentication, realtime data and image storage.
Stack
Layer	Technology
Language	Java 8
Platform	Android (minSdk 21, target 30)
Auth	Firebase Authentication
Data	Firebase Realtime Database
Storage	Firebase Storage (image upload)
UI	Material Components, ConstraintLayout, CardView, Lottie, Picasso, Neumorphism
Build	Gradle
Features

    Email/password sign-up and sign-in via Firebase Authentication
    Session handling — signed-in users land on the main feed; guests are redirected to login
    Create and browse records held in the Realtime Database
    Image upload and retrieval through Firebase Storage, rendered with Picasso
    Card-based material UI with Lottie animations and neumorphic components

Running it

This project needs its own Firebase project.

    Create a project at console.firebase.google.com
    Enable Authentication → Email/Password, Realtime Database, and Storage
    Download google-services.json and place it in app/
    Open the project in Android Studio and let Gradle sync
    Run on an emulator or device (Android 5.0+)

bash

./gradlew assembleDebug

    google-services.json is git-ignored — it is project-specific and should not be committed.

Notes

Written as coursework in 2020. It remains a working demonstration of Firebase-backed Android development: authentication, realtime sync, and media storage in one app.
License

MIT
