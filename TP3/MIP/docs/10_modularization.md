# Modularization Plan

## Goal
Separate UI from business logic and create reusable core module.

## Strategy
- Move data + repository + API to :core
- Keep UI only in :app-xml
- Create new UI in :app-compose using same core

## Benefits
- Reusability
- Cleaner architecture
- Support multiple UI paradigms