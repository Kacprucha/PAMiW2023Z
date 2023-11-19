import React, { useEffect, useState } from 'react'
import { Link, useNavigate, useParams } from 'react-router-dom'
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap'
import { tasksApi } from '../../../api/tasksApi'
import DatePicker from 'react-datepicker';

import 'react-datepicker/dist/react-datepicker.css';

export const TaskForm = () => {
  const navigate = useNavigate()
  const { taskId } = useParams()
  const [task, setTask] = useState({
    title: '',
    text: ''
  })
  const [startdate, setStartDate] = useState (new Date())

  useEffect(() => {
    if (taskId !== 'new') {
      tasksApi.getById(taskId)
        .then((res) => {
          setTask(res.data)
        })
    }
  }, [taskId])

  const handleChange = (event) => {
    const target = event.target
    const value = target.value
    const name = target.name

    setTask({ ...task, [name]: value })
  }

  const handleSubmit = async (event) => {
    event.preventDefault()

    if (task.id) {
      await tasksApi.update(task.id, task)
    } else {
      await tasksApi.create(task)
    }
    navigate('/tasks')
  }

  const title = <h2>{task.id ? 'Edit Task' : 'Add Task'}</h2>

  return (
    <Container>
      {title}
      <Form onSubmit={handleSubmit}>
        <FormGroup>
          <Label for='text'>Text</Label>
          <Input
            id='text'
            name='text'
            type='text'
            value={task.text || ''}
            onChange={handleChange}
            autoComplete='Text'
          />
        </FormGroup>
        <FormGroup>
            <DatePicker selected={startdate} onChange={(date) => setStartDate(date)} />
        </FormGroup>
        <FormGroup>
          <Button color='primary' type='submit'>Save</Button>{' '}
          <Button color='secondary' tag={Link} to='/tasks'>Cancel</Button>
        </FormGroup>
      </Form>
    </Container>
  )
}