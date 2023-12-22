import React, { useEffect, useState } from 'react'
import logo from './logo.svg'
import './Home.css'
import { notesApi } from '../../api/notesApi'
import { useAuth } from 'react-oidc-context'

export const Home = () => {
  const auth = useAuth()
  const [notes, setNotes] = useState([])

  useEffect(() => {
    console.log(auth.isAuthenticated)
    if(auth.isAuthenticated) {
      const accessToken = auth.user.access_token
      notesApi.getTop3ByUpdateDate(accessToken)
        .then((res) => {
          setNotes(res.data)
        })
        .catch(err => console.error('[Fetch Error]:', err))
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
              <TaskInstance title="Zadanie 1" text="nisancicawncaincaciawncacaicniwanc" data="10:12:2023"/>
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
      <h2 style={{ color: 'darkblue' }}>{"- " + title}</h2>
      <p style={{ color: 'darkblue' }}>{text}</p>
    </>
  )
}

const TaskInstance = (props) => {

  const {title, text, data} = props;

  return (
    <>
      <h2 style={{ color: 'darkblue'}}>{title}</h2>
      <p style={{ color: 'darkblue', textAlign: 'left' }}>{text}</p>
      <p style={{ color: 'darkblue', textAlign: 'left' }}>{data}</p>
    </>
  )
}