# Sistema Completo di Gestione Biblioteca

## Descrizione dell'Applicazione

L'obiettivo di questo progetto è creare un sistema di gestione per una biblioteca che consenta la gestione degli utenti, dei libri, dei prestiti e delle prenotazioni. L'applicazione supporta le operazioni comuni necessarie per la gestione di una biblioteca, tra cui:
- Registrazione degli utenti e gestione dei loro profili
- Aggiunta, modifica e ricerca di libri
- Gestione dei prestiti e delle prenotazioni dei libri
- Notifiche per la disponibilità dei libri prenotati e promemoria per la restituzione dei libri

## Endpoint dell'API

### Gestione degli Utenti

- **Registrazione Utente**
    - **Endpoint:** `/api/users/register`
    - **Metodo:** POST
    - **Descrizione:** Permette agli utenti di registrarsi fornendo i propri dati personali.
    - **Request Body:**
      ```json
      {
        "firstName": "Mario",
        "lastName": "Rossi",
        "email": "Mariorossi@example.com",
        "password": "pass12345",
        "dateOfBirth": "1980-06-01"
      }
      ```

- **Modifica Profilo**
    - **Endpoint:** `/api/users/{id}`
    - **Metodo:** PUT
    - **Descrizione:** Permette agli utenti di aggiornare i propri dati personali.
    - **Request Body:**
      ```json
      {
        "firstName": "Mario",
        "lastName": "Rossi",
        "email": "Mariorossi@example.com",
        "dateOfBirth": "1980-06-01"
      }
      ```

- **Visualizzazione Profilo**
    - **Endpoint:** `/api/users/{id}`
    - **Metodo:** GET
    - **Descrizione:** Permette agli utenti di visualizzare il proprio profilo, inclusi i prestiti e le prenotazioni attive.

### Gestione dei Libri

- **Aggiunta Libro**
    - **Endpoint:** `/api/books`
    - **Metodo:** POST
    - **Descrizione:** Permette agli amministratori di aggiungere nuovi libri alla biblioteca.
    - **Request Body:**
      ```json
      {
        "title": "Il Signore degli anelli",
        "author": "Tolkien",
        "publicationDate": "1954-07-29",
        "isbn": "18-12234556789",
        "copiesAvailable": 2
      }
      ```

- **Modifica Libro**
    - **Endpoint:** `/api/books/{id}`
    - **Metodo:** PUT
    - **Descrizione:** Permette agli amministratori di aggiornare le informazioni sui libri esistenti.
    - **Request Body:**
      ```json
      {
        "title": "Il Signore degli anelli",
        "author": "Tolkien",
        "publicationDate": "1954-07-29",
        "isbn": "18-12234556789",
        "copiesAvailable": 12
      }
      ```

- **Ricerca Libri**
    - **Endpoint:** `/api/books/search`
    - **Metodo:** GET
    - **Descrizione:** Permette agli utenti di cercare libri per titolo, autore o ISBN.
    - **Request Parameters:**
        - `title` (opzionale)
        - `author` (opzionale)
        - `isbn` (opzionale)

- **Visualizzazione Dettagli Libro**
    - **Endpoint:** `/api/books/{id}`
    - **Metodo:** GET
    - **Descrizione:** Permette agli utenti di visualizzare i dettagli di un libro, incluse le informazioni sulle copie disponibili.

### Gestione dei Prestiti

- **Prestito Libro**
    - **Endpoint:** `/api/loans`
    - **Metodo:** POST
    - **Descrizione:** Permette agli utenti di prendere in prestito libri se ci sono copie disponibili. Un utente non può avere più di 5 prestiti attivi contemporaneamente.
    - **Request Body:**
      ```json
      {
        "userId": 1,
        "bookId": 1
      }
      ```

- **Restituzione Libro**
    - **Endpoint:** `/api/loans/{id}`
    - **Metodo:** PUT
    - **Descrizione:** Permette agli utenti di restituire i libri presi in prestito.

- **Visualizzazione Prestiti**
    - **Endpoint:** `/api/loans/user/{userId}`
    - **Metodo:** GET
    - **Descrizione:** Permette agli utenti di visualizzare la lista dei propri prestiti attivi e storici.

### Gestione delle Prenotazioni

- **Prenotazione Libro**
    - **Endpoint:** `/api/reservations`
    - **Metodo:** POST
    - **Descrizione:** Permette agli utenti di prenotare libri non disponibili. Quando una copia diventa disponibile, la prenotazione viene convertita in prestito.
    - **Request Body:**
      ```json
      {
        "userId": 1,
        "bookId": 1
      }
      ```

- **Cancellazione Prenotazione**
    - **Endpoint:** `/api/reservations/{id}`
    - **Metodo:** DELETE
    - **Descrizione:** Permette agli utenti di cancellare le proprie prenotazioni attive.

- **Visualizzazione Prenotazioni**
    - **Endpoint:** `/api/reservations/user/{userId}`
    - **Metodo:** GET
    - **Descrizione:** Permette agli utenti di visualizzare la lista delle proprie prenotazioni attive.

### Notifiche

- **Notifiche di Disponibilità**
    - **Descrizione:** Gli utenti ricevono una notifica quando un libro prenotato diventa disponibile.

- **Promemoria di Restituzione**
    - **Descrizione:** Gli utenti ricevono promemoria per restituire i libri prima della scadenza del prestito.