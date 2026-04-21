# PlanetExpress — Piattaforma di gestione orti urbani comunitari

PlanetExpress è una **web application full-stack** sviluppata come progetto universitario per il corso di Ingegneria del Software Agile (Università degli Studi dell'Aquila). L'obiettivo era progettare e realizzare, seguendo metodologie Agile (Scrum), una piattaforma digitale per la **gestione di orti urbani condivisi**: dalla prenotazione delle zolle di terreno, alla creazione di eventi comunitari, fino a un forum di discussione interno. Il sistema modella tre livelli di utenza con flussi di lavoro distinti, rispecchiando la complessità operativa di una realtà associativa reale.

---

## Obiettivo

La sfida tecnica principale era progettare un sistema **multi-ruolo coerente** in cui le stesse entità di dominio (orto, zolla, evento) vengono percepite e gestite in modo completamente diverso da Admin, Referente e Utente — senza duplicare logica e senza esporre funzionalità non autorizzate. Il progetto affronta anche la necessità di mantenere la **business logic agnostica rispetto alla presentazione**, separando nettamente i controller API (logica) dai controller di vista (routing MVC), e di garantire una **copertura di test misurabile** attraverso l'integrazione di JaCoCo nella pipeline Maven.

---

## Tecnologie

| Area | Stack |
|---|---|
| **Linguaggio** | Java 17 |
| **Framework backend** | Spring Boot 3.2.4 (Web, Data JPA, Security) |
| **Template engine** | Thymeleaf 3 + Layout Dialect + Spring Security Extras |
| **Database** | MySQL 8.0 (Spring Data JPA / Hibernate) |
| **Sicurezza** | Spring Security 6 — BCrypt, ruoli, `CustomAuthenticationSuccessHandler` |
| **Frontend** | Tailwind CSS, Anime.js, jQuery |
| **Testing** | JUnit 5, Spring Test (MockMvc), JaCoCo 0.8.12 |
| **Build & CI** | Maven, maven-surefire-plugin |

---

## Funzionalità principali

- **Autenticazione role-based a tre livelli** — `ADMIN`, `REFERENTE`, `USER` gestiti via Spring Security con un `CustomAuthenticationSuccessHandler` che reindirizza ogni ruolo alla propria dashboard subito dopo il login, eliminando qualsiasi redirect manuale nei controller.

- **Gestione orti con workflow di approvazione** — Il Referente invia una richiesta di orto che transita per gli stati `indefinito → accettato/rifiutato` sotto controllo dell'Admin. La dashboard del Referente riflette dinamicamente lo stato corrente, mostrando azioni diverse (link attivo, badge "in attesa", form di richiesta) tramite condizionali Thymeleaf.

- **Prenotazione e coltivazione delle Zolle** — Gli Utenti occupano una `Zolla` libera (assegnazione casuale dal pool disponibile), seminano un ortaggio da un catalogo predefinito e tracciano la **percentuale di crescita** calcolata in tempo reale sulla base delle date di semina e raccolta attese — logica completamente nel dominio, senza JavaScript.

- **Sistema eventi con moderazione** — I Referenti propongono eventi (nome, luogo, prezzo, tema, durata); l'Admin approva o rifiuta. Gli Utenti consultano gli eventi per città, prenotano la propria partecipazione e possono vedere lo storico delle iscrizioni.

- **Forum comunitario con Post e Commenti** — Sezione di discussione accessibile a tutti i ruoli autenticati, con creazione di post e thread di commenti, strutturata come entità JPA con relazioni `@OneToMany`.

- **Task personali** — Ogni utente dispone di una lista di attività (`Task`) collegata al proprio profilo tramite relazione JPA, con stato di completamento tracciato e visualizzazione dedicata.

- **Architettura a doppio layer di controller** — I controller `api/` (es. `EventoController`, `ZollaController`) incapsulano tutta la logica e il dialogo con i repository; i controller di vista (`referente/`, `utente/`, `admin/`) si limitano a comporre il model Thymeleaf e restituire la view, rendendo la business logic riusabile e testabile indipendentemente dalla UI.

- **UI animata con Anime.js** — Le dashboard adottano animazioni d'ingresso sequenziali sulle card tramite Anime.js (`translateX`, `translateY`, `opacity`), con layout CSS Grid responsive a colonne variabili (`col-span` adattivo da mobile a desktop).

- **Copertura di test con JaCoCo** — La pipeline Maven esegue automaticamente la generazione del report di copertura nella fase `test`, con oltre 30 classi di test che coprono controller, modelli e repository tramite MockMvc e test unitari JUnit 5.
