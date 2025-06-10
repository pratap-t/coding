from models import app, books, db
from flask import Flask, request, jsonify

@app.route('/books', methods=['GET'])
def allbooks():
    res=books.query.all()
    lb=[]
    for i in res:
        s=i.serialize()
        lb.append(s)
    return jsonify(lb)

@app.route('/books/<id>', methods=['GET'])
def getbook(id):
    res=books.query.filter_by(bookID=id).first()
    if res is None:
        return jsonify({"error": "Book not found"}), 404
    book=res.serialize()
    return jsonify({"book":book})

@app.route('/books', methods=['POST'])
def addbook():
    book = books(request.json['bookID'], request.json['title'],
                 request.json['Author'],
                 request.json['price'])
    db.session.add(book)
    db.session.commit()
    # return redirect(url_fo('allbooks'))
    res=books.query.all()
    if res is None:
        return jsonify({"error": "Book not found"}), 404
    lb=[]
    for i in res:
        s=i.serialize()
        lb.append(s)
    return jsonify({"books":lb})

@app.route('/books/<id>', methods=['PUT'])
def updatebook(id):
    id=request.json['bookID']
    price=request.json['price']
    res=books.query.filterby(bookID=id).first()
    if res is None:
        return jsonify({"error": "Book not found"}), 404
    res.price=price
    db.session.commit()
    book=res.serialize()
    return jsonify({'book': book})

@app.route('/books/<id>', methods=['DELETE'])
def deletebook(id):
    res=books.query.filterby(bookID=id).first()
    if res is None:
        return jsonify({"error": "Book not found"}), 404
    db.session.delete(res)
    db.session.commit()
    res=books.query.all()
    lb=[]
    for i in res:
        s=i.serialize()
        lb.append(s)
    return jsonify({"books":lb})

if __name__ == '__main__':
    with app.app_context():
        db.create_all()
        app.run(debug=True)