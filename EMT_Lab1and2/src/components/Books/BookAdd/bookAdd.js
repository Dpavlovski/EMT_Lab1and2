import React from "react";
import {useNavigate} from 'react-router-dom';


const BookAdd= (props)=> {
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
            const name = formData.name;
            const category = formData.category;
            const author = formData.author;
            const availableCopies = formData.availableCopies;
            props.onAddBook(name, category, author, availableCopies);
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
                           required/>
                </div>

                <div>
                    <label htmlFor="f2">Category</label>
                    <select id="f2" name="category" onChange={handleChange}>
                        {props.categories.map((term) =>
                            <option value={term}>{term}</option>
                        )}
                    </select>
                </div>

                <div>
                    <label htmlFor="f3">Author</label>
                    <select id="f3" name="authorId" onChange={handleChange}>
                        {props.authors.map((term) =>
                            <option value={term.id}>{term.name} {term.surname}</option>
                        )}
                    </select>
                </div>

                <div>
                    <label htmlFor="f4">AvailableCopies</label>
                    <input type="number"
                           id="f4"
                           name="availableCopies"
                           onChange={handleChange}
                           required/>
                </div>

                <button id="submit" type="submit">Submit</button>
                <a id="back" href="/books" type="button" className="btn btn-primary">Back</a>
            </form>
        )
}
export default BookAdd;