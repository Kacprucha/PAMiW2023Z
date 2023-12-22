import React, { useEffect, useState } from 'react'
import logo from './logo.svg'
import './Home.css'
import { notesApi } from '../../api/notesApi'
import { tasksApi } from '../../api/tasksApi'
import { useAuth } from 'react-oidc-context'

export const Home = () => {
  const auth = useAuth()
  const [notes, setNotes] = useState([])
  const [tasks, setTasks] = useState([])

  useEffect(() => {
    console.log(auth.isAuthenticated)
    if(auth.isAuthenticated) {
      const accessToken = auth.user.access_token
      notesApi.getTop3ByUpdateDate(accessToken)
        .then((res) => {
          setNotes(res.data)
        })
        .catch(err => console.error('[Fetch Error]:', err))
      tasksApi.getTop3ByUpdateDate(accessToken)
        .then((res) => {
          setTasks(res.data)
        })
    }
  }, [auth.isAuthenticated])


  return (
    <div className='App'>
      <header className='App-header'>
        {!auth.isAuthenticated && (
          <img src={logo} className='App-logo' alt='logo' />
        )}
        {auth.isAuthenticated && (
          <div className='container'>
            <div className='box'>
              <NoteInstance title={notes[0]?.title ? notes[0].title : ""} text={notes[0]?.text ? notes[0].text : ""} />
              <NoteInstance title={notes[1]?.title ? notes[1].title : ""} text={notes[1]?.text ? notes[1].text : ""} />
              <NoteInstance title={notes[2]?.title ? notes[2].title : ""} text={notes[2]?.text ? notes[2].text : ""} />
            </div>

            <div className='box'>
              <TaskInstance title={"1"} text={tasks[0]?.text ? tasks[0].text : ""} data={tasks[0]?.deadline ? tasks[0].deadline : ""}/>
              <TaskInstance title={"2"} text={tasks[1]?.text ? tasks[1].text : ""} data={tasks[1]?.deadline ? tasks[1].deadline : ""}/>
              <TaskInstance title={"3"} text={tasks[2]?.text ? tasks[2].text : ""} data={tasks[2]?.deadline ? tasks[2].deadline : ""}/>
            </div>
          </div>
        )}
      </header>
    </div>
  )
}

const NoteInstance = (props) => {

  const {title, text} = props;

  return (
    <>
      <h2 style={{ color: 'black' }}>{"- " + title}</h2>
      <p style={{ color: 'black' }}>{text}</p>
    </>
  )
}

const TaskInstance = (props) => {
  const {title, text, data} = props;
  let color = '';
  let dataDate = new Date(data)

  const currentDate = new Date();
  const differenceInDays = Math.floor(
    (dataDate - currentDate) / (1000 * 60 * 60 * 24)
  );

  if (differenceInDays >= 5) {
    color = 'black';
  } else if (differenceInDays >= 1) {
    color ='orange';
  } else {
    color ='red';
  }

  console.log(color + " | " + differenceInDays)

  return (
    <>
      <h2 style={{ color: 'black'}}>{text.length > 0 ? "Task " + title : ""}</h2>
      <p style={{ color: 'black', textAlign: 'left' }}>{text}</p>
      <p style={{ color: color, textAlign: 'left' }}>{text.length > 0 ? data : ""}</p>
    </>
  )
}