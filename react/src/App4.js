import React, {useState, useEffect} from 'react';



function App() {
    const [message, setMessage] = useState("");

    useEffect(() => {
      fetch('/hello')
          .then(response => response.text())
          .then(message => {
              setMessage(message);
          });
    },[])
    return (
      <div className="App">
          <header className="App-header">
            
              <h1 className="App-title">{message}</h1>
          </header>
          <p className="App-intro">
              To get started, edit <code>src/App.js</code> and save to reload.
          </p>
      </div>
    )
}

export default App;