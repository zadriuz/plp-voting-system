# PLP Voting Management System

Desktop campus election and ballot tabulation application built with Java Swing and MySQL, featuring role-based student voting, candidate party profiles, election phase controls, and live vote tallying.

> **Status:** Source code, MySQL schema, and configuration templates are preserved for reference.

<!--
### Interface Walkthrough

| Student Login | Candidate & Party Profiles | Ballot Casting & Review | Live Tally Dashboard |
| :---: | :---: | :---: | :---: |
| ![Login](screenshots/login.png) | ![Candidates](screenshots/candidates.png) | ![Ballot](screenshots/voting-form.png) | ![Dashboard](screenshots/dashboard.png) |
| *Student number & passcode verification* | *Party-list lineup, credentials & platforms* | *Position-based ballot selection & review* | *Real-time vote counts & election status* |
-->

---

### Role & Context

- **Role:** Lead Developer
- **Use Case:** Campus student council election management at Pamantasan ng Lungsod ng Pasig (PLP)
- **Platform:** Java Desktop Application (NetBeans / Java Swing)

---

### Key Features

- **Voter Authentication & Single-Vote Enforcement:** Authenticates voters by `student_number` and `passcode`, tracking `has_voted` flags to prevent duplicate ballots.
- **Election Phase Management:** Controls election state (`OPEN` / `CLOSED`) via `tblelectionstatus` to lock or unlock ballot submission.
- **Candidate & Party Browsing:** Displays candidates grouped by position and party list along with their credentials and platform (`plataporma`).
- **Guided Ballot Casting & Review:** Allows students to select candidates up to each position's `max_votes` limit and verify choices on a review screen before final submission.
- **Admin Utilities & Results Dashboard:** Supports voter/admin credential generation (`AdminGeneratorForm`) and real-time ballot aggregation (`VotingDashboard`).

---

### Tech Stack & Database Architecture

| Component | Technology |
| :--- | :--- |
| **Language** | Java |
| **Desktop UI** | Java Swing / AWT (Custom rounded components & forms) |
| **Database** | MySQL 8.0 (`JDBC`) |
| **IDE / Build** | Apache NetBeans |

#### MySQL Schema (`plpvotingdb`)

| Table | Purpose | Key Columns |
| :--- | :--- | :--- |
| **`tblvoters`** | Registered student voter records and vote status | `student_number` (PK), `passcode`, `has_voted`, `course`, `year_level`, `section` |
| **`tblpositions`** | Elective offices and per-position vote limits | `position_id` (PK), `position_name` (UNIQUE), `max_votes` |
| **`tblcandidates`** | Candidate profiles, party affiliations, and platforms | `candidate_id` (PK), `full_name`, `party_list`, `position_id`, `credentials`, `plataporma` |
| **`tblvotes`** | Anonymized cast ballot entries linked to candidates | `vote_id` (PK), `candidate_id` |
| **`tblelectionstatus`** | Global election phase toggle | `status_id` (PK), `current_phase` |

#### Repository Structure

```text
plp-voting-system/
├── PLPVotingSystem/
│   ├── src/
│   │   ├── database/
│   │   │   └── DBConnection.java      # Externalized JDBC connection loader
│   │   └── plpvotingsystem/           # Swing forms, dashboards, and custom UI components
│   └── db.properties.example          # Sanitized database configuration template
└── database/
    └── plpvotingdb.sql                # MySQL schema and synthetic test seed data
```

---

### Local Setup (Reference)

1. Import `database/plpvotingdb.sql` into a local **MySQL 8.0+** instance to create the `plpvotingdb` schema.
2. Copy `PLPVotingSystem/db.properties.example` to `PLPVotingSystem/db.properties` and enter your local MySQL credentials:
   ```properties
   db.url=jdbc:mysql://localhost:3306/plpvotingdb
   db.user=root
   db.password=YOUR_MYSQL_PASSWORD
   ```
3. Open the `PLPVotingSystem` project in **NetBeans**, ensure the MySQL Connector/J driver is attached to Libraries, and run `PLPVotingSystem.java`.
