import React, { useEffect, useState } from 'react'
import { Link, useNavigate, useParams } from 'react-router-dom'
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap'
import { notesApi } from '../../../api/notesApi'
import { useAuth } from 'react-oidc-context'

export const NoteForm = () => {
  const auth = useAuth()
  const accessToken = auth.user.access_token
  const navigate = useNavigate()
  const { noteId } = useParams()
  const [note, setNote] = useState({
    locked: false,
    title: '',
    text: ''
  })
  const [isChecked, setIsChecked] = useState(Boolean(note.locked));

  useEffect(() => {
    if (noteId !== 'new') {
      notesApi.getById(noteId, accessToken)
        .then((res) => {
          setNote(res.data)
        })
    }
  }, [noteId, accessToken])

  const handleChange = (event) => {

    const target = event.target
    const name = target.name
    let value = target.value

    if(target.name === 'locked')
    {
      setIsChecked(!isChecked)
      value = !isChecked
    }

    setNote({ ...note, [name]: value })
  }

  const handleSubmit = async (event) => {
    event.preventDefault()

    if (note.id) {
      await notesApi.update(note.id, note, accessToken)
    } else {
      await notesApi.create(note, accessToken)
    }
    navigate('/notes')
  }

  const title = <h2>{note.id ? 'Edit note' : 'Add Note'}</h2>

  return (
    <Container>
      {title}
      <Form onSubmit={handleSubmit}>
        <FormGroup>
          <Label for='title'>Title</Label>
          <Input
            id='title'
            name='title'
            type='text'
            value={note.title || ''}
            onChange={handleChange}
            autoComplete='Title'
          />
        </FormGroup>
        <FormGroup>
          <Label for='text'>Text</Label>
          <Input
            id='text'
            name='text'
            type='text'
            value={note.text || ''}
            onChange={handleChange}
            autoComplete='Text'
          />
        </FormGroup>
        <FormGroup check>
          <Label check>
            <Input 
              id='private'
              name='locked' 
              type='checkbox' 
              checked={isChecked}
              value={note.locked}
              onChange={handleChange}/>{' '}
            Is it private note
          </Label>
        </FormGroup>
        <FormGroup>
          <Button color='primary' type='submit'>Save</Button>{' '}
          <Button color='secondary' tag={Link} to='/notes'>Cancel</Button>
        </FormGroup>
      </Form>
    </Container>
  )
}