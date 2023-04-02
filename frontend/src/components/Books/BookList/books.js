import React,{Component} from "react";
import BookTerm from '../BookTerm/bookTerm';
import {Link} from "react-router-dom";
import ReactPaginate from "react-paginate";

class Books extends Component{
    constructor(props) {
        super(props);

        this.state={
            page:0,
            size:5,
        }
    }

    render() {
        const offset= this.state.size * this.state.page;
        const nextPageOffset = offset + this.state.size;
        const pageCount=Math.ceil(this.props.books.length / this.state.size);
        const books = this.getBooksPage(offset,nextPageOffset);
        return (
                <div className={"container mm-4 mt-5"}>
                    <div className={"row"}>
                        <table  className={"table table-stripped"}>
                            <thead>
                            <tr>
                                <th scope={"col"}>Name</th>
                                <th scope={"col"}>Category</th>
                                <th scope={"col"}>Author</th>
                                <th scope={"col"}>Available Copies</th>
                            </tr>
                            </thead>
                            <tbody>
                            {books}
                            </tbody>
                        </table>
                    </div>
                    <Link to={"/books/add"}><button>Add new book</button></Link>

                    <ReactPaginate
                        pageCount={pageCount}
                        onPageChange={this.handlePageClick}
                    />
                </div>
        )
    }

    handlePageClick = (data)=> {
        let selected = data.selected;
        this.setState({
            page:selected
        })
    }
    getBooksPage = (offset,nextPageOffset)=>{
        return this.props.books.map((term) => {
            return (
                <BookTerm term={term} onDelete={this.props.onDelete.bind(this, term.id)} onEdit={this.props.onEdit} onMarkAsTaken={this.props.onMarkAsTaken}/>
            )
        }).filter((book,index)=>{
            return index >= offset && index < nextPageOffset;
        })

    }
}

export default Books;