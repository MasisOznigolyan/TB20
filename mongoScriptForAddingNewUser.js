
const name="Ahmet";
const emailAddress="Ahmet@gmail.com";
const categoryName="euroleague";
const categoryUrl="https://trendbasket.net/tag/euroleague/";
const lastNewsUrl="https://trendbasket.net/turkish-airlines-euroleague-takimlarinin-2023-24-sezonu-kadrolari/";

function generateCustomUniqueId() {
  const objectId = ObjectId();

	// Convert the ObjectID to a string
	const uniqueId = objectId.toString();
  return uniqueId;
}

db.users.insertOne({
	userId:generateCustomUniqueId(),
	name: name
})

db.Email.insertOne({
	emailId:generateCustomUniqueId(),
	eMail:emailAddress
})

const user= db.users.findOne({ name: name });
const userId=user._id;

const email=db.Email.findOne({eMail:emailAddress});
const emailId=email._id;

const userEmailId=generateCustomUniqueId()
db.userEmail.insertOne({
	userEmailId:userEmailId,
	userId:{
		$ref: "users",		
		$id:userId
			},
	emailId:{
		$ref: "Email",		
		$id:emailId
		}
})


db.NewsCategory.insertOne({
		newsCategoryId:generateCustomUniqueId(),
		name:categoryName,
		categoryUrl:categoryUrl

})

const newsCategory=db.NewsCategory.findOne({categoryUrl:categoryUrl});
const newsCategoryId=newsCategory._id;

db.UserCategory.insertOne({
	userCategoryId:generateCustomUniqueId(),
	userId:{
		$ref: "users",		
		$id:userId
		},
	newsCategoryId:{
		 $ref: "NewsCategory",
		 $id:newsCategoryId	
		}


})

db.News.insertOne({
	newsId:generateCustomUniqueId(),
	url:lastNewsUrl,
	newsCategoryId:{
		$ref:"NewsCategory",
		$id:newsCategoryId
		}

})

const news=db.News.findOne({url:lastNewsUrl});
const newsId=news._id;

const userEmail=db.userEmail.findOne({userEmailId:userEmailId});
const id=userEmail._id;

db.LastSent.insertOne({
	LastSentId:generateCustomUniqueId(),
	userEmailId:{
		$ref:"userEmail",		
		$id:id
		},
	newsId:{
		$ref:"News",
		$id:newsId
		}


})


