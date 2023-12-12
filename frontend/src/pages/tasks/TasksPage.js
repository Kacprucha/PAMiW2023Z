import React, { useEffect, useState } from 'react'
import { Button, ButtonGroup, Container, Table } from 'reactstrap'
import { Link } from 'react-router-dom'
import { tasksApi } from '../../api/tasksApi'
import { useAuth } from 'react-oidc-context'

export const TasksPage = () => {
  const auth = useAuth()
  const accessToken = auth.user.access_token
  const [tasks, setTasks] = useState([])

  useEffect(() => {
    tasksApi.getAll(accessToken)
      .then((res) => {
        setTasks(res.data)
      })
      .catch(err => console.error('[Fetch Error]:', err))
  }, [])

  const remove = (id) => {
    tasksApi.delete(id,accessToken)
      .then(() => {
        setTasks((tasks) => tasks.filter((note) => note.id !== id))
      })
  }

  const taskList = tasks.map(task => {
    return (
      <tr key={task.id}>
        <td style={{ whiteSpace: 'nowrap' }}>{task.id}</td>
        <td style={{ whiteSpace: 'nowrap' }}>{task.text}</td>
        <td style={{ whiteSpace: 'nowrap' }}>{task.deadline}</td>
        <td align='center'>
          <ButtonGroup>
            <Button size='sm' color='primary' tag={Link} to={'/tasks/' + task.id}>
              Edit
            </Button>
            <Button size='sm' color='danger' onClick={() => remove(task.id)}>
              Delete
            </Button>
          </ButtonGroup>
        </td>
      </tr>
    )
  })

  return (
    <div>
      <Container fluid>
        <h3>Tasks</h3>
        <Table striped bordered hover size='sm'>
          <thead>
            <tr>
              <th width='80px'>Id</th>
              <th>Text</th>
              <th width='300px'>To do date</th>
              <th width='120px'>
                <div align='center'>Action</div>
              </th>
            </tr>
          </thead>
          <tbody>
            {taskList}
          </tbody>
        </Table>
        <div className='float-right'>
          <Button color='success' tag={Link} to='/tasks/new'>
            Add
          </Button>
        </div>
      </Container>
    </div>
  )
}