import React, { useEffect, useState } from "react";
import "./App.css";

const API = "http://localhost:8080/api/v1/todos";

export default function App() {
  const [todos, setTodos] = useState([]);
  const [title, setTitle] = useState("");

  useEffect(() => {
    loadTodos();
  }, []);

  const loadTodos = async () => {
    const res = await fetch(API);
    const data = await res.json();
    setTodos(data);
  };

  const addTodo = async () => {
    if (!title.trim()) return;

    await fetch(API, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ title })
    });

    setTitle("");
    loadTodos();
  };

  const toggleTodo = async (id, completed) => {
    await fetch(`${API}/${id}`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ completed: !completed })
    });

    loadTodos();
  };

  const deleteTodo = async (id) => {
    await fetch(`${API}/${id}`, { method: "DELETE" });
    loadTodos();
  };

  return (
      <div className="app">
        <h1>Todo App ✅</h1>

        <div className="inputBar">
          <input
              value={title}
              onChange={(e) => setTitle(e.target.value)}
              placeholder="Enter todo..."
          />
          <button onClick={addTodo}>Add</button>
        </div>

        <div className="todoList">
          {todos.map(todo => (
              <div key={todo.id} className="todoCard">
                <div className="todoInfo">
              <span className={todo.completed ? "done" : ""}>
                {todo.title}
              </span>

                  <div className="meta">
                    <span>{new Date().toLocaleDateString()}</span>
                    <span>{new Date().toLocaleTimeString()}</span>
                  </div>
                </div>

                <div className="actions">
                  <button onClick={() => toggleTodo(todo.id, todo.completed)}>
                    {todo.completed ? "Undo" : "Done"}
                  </button>

                  <button className="delete" onClick={() => deleteTodo(todo.id)}>
                    ✖
                  </button>
                </div>
              </div>
          ))}
        </div>
      </div>
  );
}
