# Anime Tracker

Der Anime Tracker ist eine moderne Full-Stack-Webanwendung, mit der Benutzer ihre Anime-Sammlung verwalten und ihren Fortschritt beim Anschauen verfolgen können.

## Nutzung
Zum Tracken der Anime auf **https://anime-tracker-frontend.onrender.com/** gehen und einen Account registrieren, danach kann mithilfe der Such-Funktion der Anime hinzugefügt und bearbeitet werden.

## Technologie-Stack

### Backend
- **Java 21** mit **Spring Boot 3**
- **Spring Data JPA** für Datenbankzugriff
- **Spring Security** für Authentifizierung
- **Gradle** als Build-Tool
- **Lombok** zur Codereduzierung
- **JUnit 5** für Tests

### Frontend
- **Vue.js 3** mit Composition API
- **Fetch API** für HTTP-Requests
- **Jest** für Unit-Tests

### Datenbank
- **H2** für die Entwicklung
- **PostgreSQL** für die Produktionsumgebung

### Deployment
- Backend und Frontend werden auf **Render** gehostet

## Kernfunktionen

- **Benutzerregistrierung und Login:** Einfache Benutzerverwaltung ohne Passwort (Demo-Zwecke)
- **Anime-Suche:** Externe Jikan API-Integration zur Suche nach Animes
- **Fortschrittsverfolgung:** Zählung gesehener Episoden mit schnellen Plus/Minus-Funktionen
- **Favoriten:** Markieren und Filtern von Lieblings-Animes
- **Responsive Design:** Optimiert für Desktop und mobile Geräte


### Backend
- **Controller:** REST-Endpunkte für Anime, Benutzer und Favoriten
- **Model:** JPA-Entitäten für Datenbank-Mapping
- **Repository:** Spring Data-Repositories für Datenbankzugriffe

### Frontend
- **Komponenten:** Wiederverwendbare Vue-Komponenten (AnimeItem, AuthForm)
- **Services:** Hilfsfunktionen für API-Aufrufe und Authentifizierung

## Entwicklung

### Voraussetzungen
- JDK 21
- Node.js und npm
- IDE (empfohlen: IntelliJ IDEA)

### Backend starten
```bash
./gradlew bootRun
```

### Frontend starten
```bash
npm install
npm run serve
```

## Testen

Die Anwendung enthält umfangreiche Tests:

### Backend-Tests
```bash
./gradlew test
```

### Frontend-Tests
```bash
npm run test:unit
```


