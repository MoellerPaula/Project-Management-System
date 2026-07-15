# Existing Systems Analysis

## Purpose

This document analyzes existing project management systems to identify relevant concepts, features and workflows for the Project Management System.

The goal is not to replicate existing solutions, but to understand established approaches and derive design decisions for the development of this application.

---

# Analyzed Systems

## Jira

### Overview

Jira is a project management and issue tracking tool mainly used by software development teams to organize work, manage tasks and track progress.

### Relevant Concepts

#### Tasks / Issues

Tasks represent individual work items within a project.

Tasks can contain:

* Title
* Description
* Priority
* Unique identifier
* Author
* Assigned user
* Sub-tasks

Tasks can be assigned to team members and used to track individual development activities.

#### Workflow Management

Tasks follow a status-based workflow and are displayed on a Kanban board.

Examples of statuses:

* To Do
* In Progress
* Done

The Kanban board visualizes tasks based on their current status.

#### Progress Tracking

Jira provides reports to analyze project progress based on task completion and workflow states.

### Relevant Concepts for this Project

* Tasks as the central entity for managing work items
* Status-based workflows
* Assignment of tasks to users
* Task hierarchy through sub-tasks
* Progress tracking based on completed tasks

---

## Linear

### Overview

Linear is a project management tool focused on software development teams and provides a simplified approach to issue tracking and project planning.

### Relevant Concepts

#### Projects and Issues

Work is organized through projects containing multiple issues.

Issues represent development tasks and can be assigned to team members.

#### Simple Workflow Management

Linear uses lightweight workflows to manage task progress.

Tasks move through defined states such as:

* Backlog
* Todo
* In Progress
* Completed

#### Project Progress

Projects provide an overview of completed and remaining work.

### Relevant Concepts for this Project

* Clear separation between projects and tasks
* Simple task workflows
* Focus on developer-oriented processes
* Project progress overview

---

## GitHub Projects

### Overview

GitHub Projects integrates project management directly into software repositories and supports planning through issues, boards and custom workflows.

### Relevant Concepts

#### Issue-Based Development

Work items are represented as issues that can be:

* Created
* Assigned
* Prioritized
* Tracked through different states

#### Kanban Workflow

Issues can be organized using boards with different workflow states.

Example:

* Backlog
* In Progress
* Done

#### Integration with Development Workflow

Project management is directly connected to:

* Source code
* Pull requests
* Commits

### Relevant Concepts for this Project

* Issue-based task management
* Connection between development work and project tasks
* Workflow-based organization

---

## YouTrack

### Overview

YouTrack is an issue tracking and project management tool developed by JetBrains, supporting software development workflows.

### Relevant Concepts

#### Customizable Workflows

Tasks can follow customizable workflows depending on project requirements.

#### User Roles

Different users can have different responsibilities and permissions.

#### Task Organization

Tasks support:

* Assignment
* Priorities
* Status tracking
* Relationships between tasks

### Relevant Concepts for this Project

* Flexible workflows
* User roles
* Permission concepts
* Task relationships

---

# Analysis Results

Based on the analyzed systems, the following core concepts are relevant for the Project Management System.

## Core Entities

The application should include the following main entities:

### User

Users represent members of a development team.

Potential attributes:

* ID
* Name
* Email
* Role

Users can participate in multiple projects and be assigned tasks.

---

### Project

Projects organize related tasks and team members.

Potential attributes:

* ID
* Name
* Description
* Status

Projects contain multiple tasks and users.

---

### Task

Tasks represent individual work items within a project.

Potential attributes:

* ID
* Title
* Description
* Priority
* Status
* Created date

Tasks can be assigned to users and belong to a project.

---

### Role

Roles define responsibilities within projects.

Examples:

* Project Manager
* Developer
  
Project roles will initially describe the responsibility of a user within a project.

Permission-based access control is planned for a later development phase.

---

# Design Decisions

Based on the analysis, the following decisions are made:

## Task-Centered Workflow

Tasks are the central element of the application because software development work is primarily organized around individual work items.

## Status-Based Progress Tracking

Task progress will be represented through defined statuses instead of manually entered progress values.

Example:

* TODO
* IN_PROGRESS
* DONE

The overall project progress can be derived automatically from the completion status of all project tasks.

## Project-Based Organization

Projects act as containers for:

* Tasks
* Users
* Development activities

## User Assignment

Tasks can be assigned to users to represent responsibilities within a project.

## Incremental Feature Development

Advanced concepts such as:

* Notifications
* Detailed permissions
* Reporting dashboards
* External integrations

will not be part of the initial version but may be added in future development phases.

---

# Conclusion

The analysis of existing project management systems shows that modern software development tools share common concepts such as projects, tasks, users, workflows and progress tracking.

These concepts will serve as the foundation for the domain model and database design of the Project Management System.
