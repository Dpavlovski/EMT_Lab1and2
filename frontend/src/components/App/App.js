import './App.css';
import React,{Component} from "react";
import {BrowserRouter as Router, Route, Routes,Navigate} from "react-router-dom";
import LibraryService from "../../repository/LibraryRepository";
import Books from "../Books/BookList/books";
import Authors from "../Authors/AuthorList/authors";
import Categories from "../Categories/categories";
import Header from "../Header/header";
import BookAdd from "../Books/BookAdd/bookAdd";
import BookEdit from "../Books/BookEdit/bookEdit";

class App extends Component {
    constructor(props) {
        super(props);
        this.state={
            books: [],
            authors: [],
            categories: [],
            selectedBook: {}
        }
    }

    render() {
        return (
           <Router>
              <Header/>
              <main>
                  <div>
                      <Routes>
                          <Route path={"/books/add"} element={<BookAdd categories={this.state.categories} authors={this.state.authors} onAddBook={this.addBook}/>}/>
                          <Route path={"/books/edit/:id"} element={<BookEdit categories={this.state.categories} authors={this.state.authors} onEditBook={this.editBook} book={this.state.selectedBook}/>}/>
                          <Route path={"/books"} element={<Books books={this.state.books} onDelete={this.deleteBook} onEdit={this.getBook} onMarkAsTaken={this.markAsTakenBook}/>}/>
                          <Route path={"/authors"} element={<Authors authors={this.state.authors}/>}/>
                          <Route path={"/categories"} element={<Categories categories={this.state.categories}/>}/>
                          <Route index element={<Navigate to={"/books"} />} />
                      </Routes>
                  </div>
              </main>
           </Router>
        );
    }

    loadBooks = () => {
        LibraryService.fetchBooks()
            .then((data) => {
                this.setState({
                    books: data.data
                })
            });
    }

    loadAuthors= () => {
        LibraryService.fetchAuthors()
            .then((data) => {
                this.setState({
                    authors: data.data
                })
            });
    }

    loadCategories= () => {
        LibraryService.fetchCategories()
            .then((data) => {
                this.setState({
                    categories: data.data
                })
            });
    }

    deleteBook= (id)=>{
         LibraryService.deleteBook(id)
             .then( ()=> {
                 this.loadBooks();
        });
    }


    addBook= (name,category,author,availableCopies)=> {
        LibraryService.addBook(name,category,author,availableCopies)
            .then(()=> {
                this.loadBooks();
            });
    }

    getBook= (id)=>{
         LibraryService.getBook(id)
            .then((data)=>{
                this.setState({
                    selectedBook:data.data
                });
            })
    }
    editBook=(id,name,category,author,availableCopies)=>{
         LibraryService.editBook(id,name,category,author,availableCopies)
            .then(()=>{
                this.loadBooks()
            })
    }

    markAsTakenBook= (id) => {
         LibraryService.markAsTakenBook(id)
            .then(()=>{
                this.loadBooks();
            })
    }
    componentDidMount() {
        this.loadBooks();
        this.loadAuthors();
        this.loadCategories();
    }
}
export default App;
