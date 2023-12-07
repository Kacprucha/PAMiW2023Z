import React from 'react'
import { Route, Routes } from 'react-router-dom'
import { NavBar } from './components/NavBar'
import { Home } from './pages/home/Home'
import { NotesPage } from './pages/notes/NotesPage'
import { NoteForm } from './pages/notes/form/NoteForm'
import { TasksPage } from './pages/tasks/TasksPage'
import { TaskForm } from './pages/tasks/form/TaskForm'
import { ProtectedRoute } from './components/ProtectedRoute'

export default function App () {
  return (
    <>
      <NavBar />
      <Routes>
        <Route index element={<Home />} />
        <Route element={<ProtectedRoute />}>
          <Route path='/notes' element={<NotesPage />} />
          <Route path='/notes/:noteId' element={<NoteForm />} />
          <Route path='/tasks' element={<TasksPage />} />
          <Route path='/tasks/:taskId' element={<TaskForm />} />
        </Route>
      </Routes>
    </>
  )
}