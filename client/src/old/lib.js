function Object.cast(rawObj, constructor)
{
    var obj = new constructor();
    for(var i in rawObj)
        obj.i = rawObj.i;
    return obj;
}
var fooJSON = Object.cast(jQuery.parseJSON({"a":4, "b": 3}), Foo);