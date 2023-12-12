import { backendApi } from './backendApi'
import { bearerAuth } from './bearerAuth'

const tasksClient = backendApi('/tasks')

export const tasksApi = {
  getAll (token) {
    console.log('Fetching tasks')
    return tasksClient.get('', {
      headers: { Authorization: bearerAuth(token) }
    })
  },

  getById (id, token) {
    console.log('Get task', id)
    return tasksClient.get(`/${id}`, {
      headers: { Authorization: bearerAuth(token) }
    })
  },

  create (task, token) {
    console.log('Create task', task)
    return tasksClient.post('', task, {
      headers: { Authorization: bearerAuth(token) }
    })
  },

  update (id, task, token) {
    console.log('Update task', id, task)
    return tasksClient.put(`/${id}`, task, {
      headers: { Authorization: bearerAuth(token) }
    })
  },

  delete (id, token) {
    console.log('Delete task', id)
    return tasksClient.delete(`/${id}`, {
      headers: { Authorization: bearerAuth(token) }
    })
  }
}