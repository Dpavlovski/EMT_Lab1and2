import React from "react";
import {useNavigate} from "react-router-dom";

const BookEdit= (props)=> {
    const navigate = useNavigate();
    const [formData, updateFormData] = React.useState({
        name: "",
        category: "CLASSICS",
        author: 1,
        availableCopies: 0
    })
    const handleChange = (e) => {
        updateFormData({
            ...formData,
            [e.target.name]: e.target.value.trim()
        })
    }
    const onFormSubmit = (e) => {
        e.preventDefault();
        const name = formData.name !== "" ? formData.name : props.book.name;
        const category = formData.category !== "CLASSICS" ? formData.category :props.book.category;
        const author = formData.author !== 1 ? formData.author : props.book.author.id;
        const availableCopies = formData.availableCopies !== 0 ? formData.availableCopies : props.book.availableCopies;
        props.onEditBook(props.book.id,name, category, author, availableCopies);
        navigate("/books");
    }

    return (

        <form onSubmit={onFormSubmit}>
            <div>
                <label htmlFor="f1">Name</label>
                <input type="text"
                       id="f1"
                       name="name"
                       onChange={handleChange}
                       placeholder={props.book.name}
                       required/>
            </div>

            <div>
                <label htmlFor="f2">Category</label>
                <select id="f2" name="category" onChange={handleChange}>
                    {props.categories.map((term) =>
                        <option selected={props.book.id} value={term}>{term}</option>
                    )}
                </select>
            </div>

            <div>
                <label htmlFor="f3">Author</label>
                <select id="f3" name="authorId" onChange={handleChange} >
                    {props.authors.map((term) =>{
                        if(props.book.author !== undefined && props.book.author.id===term.id)
                           return <option selected={props.book.author.id} value={term.id}>{term.name} {term.surname}</option>
                        else return <option value={term.id}>{term.name} {term.surname}</option>
                })}
                </select>
            </div>

            <div>
                <label htmlFor="f4">AvailableCopies</label>
                <input type="number"
                       id="f4"
                       name="availableCopies"
                       onChange={handleChange}
                       placeholder={props.book.availableCopies}
                       required/>
            </div>

            <button id="submit" type="submit">Submit</button>
            <a id="back" href="/books" type="button" className="btn btn-primary">Back</a>
        </form>
    )
}
export default BookEdit;