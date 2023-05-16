import './App.css';
import { VATStatusComponent } from './components/VATStatusComponent';
import VATValidationUI from './components/VATValidationUI';

function App() {
  return (
    <div className="App">
      <header className="App-header">
       <VATValidationUI></VATValidationUI>
       <VATStatusComponent></VATStatusComponent>
      </header>
    </div>
  );
}

export default App;
