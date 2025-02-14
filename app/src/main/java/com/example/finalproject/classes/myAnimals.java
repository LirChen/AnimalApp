package com.example.finalproject.classes;

import android.content.Context;
import android.content.res.Resources;

import com.example.finalproject.R;

import java.util.Locale;

public class myAnimals {
    private static String[] nameArray;
    private static String[] typeArray;
    private static String[] descriptionArray;
    public static Integer[] drawableArray = {R.drawable.lion, R.drawable.cat, R.drawable.fish,
            R.drawable.mouse, R.drawable.shark, R.drawable.dog,
            R.drawable.dolfin, R.drawable.monkey, R.drawable.duck, R.drawable.bird};

    public static Integer[] id_ = {0,1,2,3,4,5,6,7,8,9};

    public static void initializeArrays(Context context) {
        // בדיקת השפה הנוכחית
        String currentLanguage = Locale.getDefault().getLanguage();

        if (currentLanguage.equals("iw")) {
            // עברית
            nameArray = new String[]{"אריה", "חתול", "דג", "עכבר", "כריש", "כלב","דולפין" ,"קוף", "ברווז", "ציפור"};
            typeArray = new String[]{"יונקים","יונקים","דגים","יונקים","דגים","יונקים","יונקים","יונקים","עופות","עופות"};
            descriptionArray = new String[]{
                    "אריה הוא מין של טורף גדול מהסוג פנתר שבמשפחת החתוליים, והוא השני בגודלו בין בני הקבוצה דמויי חתול, אחרי תת-המין הסיבירי של הטיגריס.",
                    "חתול הבית הוא יונק טורף מבוית, מהסוג חתול ממשפחת החתוליים. החתול נפוץ בכל יבשות העולם מלבד אנטארקטיקה. משערים כי מוצאו מחתול הבר. החתול הוא טורף לילי הצד מכרסמים, ציפורים ודגים.",
                    "דָּג הוא בעל חיים בעל חוליות, המשתייך לקבוצה של למעלה מ-35,700 מינים שונים, שחיים במים מתוקים ומים מלוחים. המונח דג מתאר צורת חיים ולא קבוצה טקסונומית ומתייחס למגוון בעלי חוליות מכמה קווים אבולוציוניים.",
                    "עכבר הוא סוג יונק קטן מסדרת המכרסמים. מין העכבר הנפוץ ביותר הוא העכבר המצוי, המצוי כמעט בכל העולם. כל מיני העכברים חיים בסביבת אדם.",
                    "דמויי כריש היא אולטרה-סדרה בתוך מחלקת דגי הסחוס (סחוסיים) של מינים שונים החיים בימים ובאוקיינוסים והם מהטורפים הבולטים בהם.",
                    "כלב הוא שמו היום-יומי של כלב הבית, תת-מין של הזאב המצוי, ממשפחת הכלביים, מסדרת היונקים הטורפים. הכלב התפתח מהזאב, והוא בעל החיים המבוית הקדום ביותר.",
                    "הדולפינים הם קבוצה פרפילטית של יונקים ימיים השייכים לסדרת הלווייתנאים, ולתת-סדרה לווייתני שיניים. הם מתחלקים לשתי על-משפחות: דולפינים ובה משפחת דולפינים יחידה – דולפיניים, ודולפיני נהרות, ובה ארבע משפחות קטנות ומיוחדות.",
                    "קופים הוא שם עממי לאוסף מיני בעלי חיים מסדרת הפרימטים, אשר במובן המדעי כולל את תת-הסדרה קופים רחבי-אף ואת המשפחה קופים בעלי זנב, אך אינו כולל את קופי האדם ואת האדם עצמו, אף שהם קרובים אבולוציונית.",
                    "ברווז הוא שם כללי למספר מינים במשפחת הברווזיים. הברווזים אינם קבוצה משותפת אך הם בכל זאת שונים מסוגים אחרים במשפחה כמו אווזים וברבורים בכך שהם בעלי גוף קטן יותר, צוואר קצר יותר ומקור גדול יותר.",
                    "העופות מכוסים בנוצות, תכונה המייחדת אותם מכל שאר בעלי החיים בני־ימינו, והגפיים הקדמיות שלהם הן כנפיים המאפשרות למרביתם לעופף באוויר."
            };
        } else {
            // אנגלית (ברירת מחדל)
            nameArray = new String[]{"Lion", "Cat", "Fish", "Mouse", "Shark", "Dog","Dolfin" ,"Monkey", "Duck", "Bird"};
            typeArray = new String[]{"Mammals", "Mammals", "Fishes", "Mammals", "Fishes", "Mammals","Mammals" ,"Mammals", "Aves", "Aves"};
            descriptionArray = new String[]{
                    "The lion is a species of large predator from the genus Panthera in the family Felidae. It is the second-largest member of the feline-like group (Feliformia), after the Siberian subspecies of the tiger.",
                    "The domestic cat is a domesticated carnivorous mammal from the Felis genus in the Felidae family. Cats are found on every continent except Antarctica and are believed to have originated from the wildcat (Felis lybica). They are nocturnal predators, hunting rodents, birds, and fish.",
                    "A fish is a vertebrate animal belonging to a group of over 35,700 different species that live in freshwater and saltwater. The term fish describes a life form rather than a taxonomic group and refers to a variety of vertebrates from multiple evolutionary lineages.",
                    "A mouse is a small mammal of the rodent order. The most common species is the house mouse, which is found almost worldwide. All mouse species live in human-associated environments.",
                    "Shark-like fishes are an ultra-order within the class of cartilaginous fish (Chondrichthyes). They include various species that inhabit seas and oceans and are among the top predators in these environments.",
                    "A dog is the common name for the domestic dog, a subspecies of the gray wolf, belonging to the Canidae family in the order of carnivorous mammals. The dog evolved from the wolf and is considered the earliest domesticated animal.",
                    "Dolphins are a paraphyletic group of marine mammals belonging to the order Cetacea and the suborder Odontoceti (toothed whales). They are divided into two superfamilies: Dolphins, which include a single family – Delphinidae (oceanic dolphins) River dolphins, which consist of four small and distinct families.",
                    "Monkeys is a common name for a collection of species from the order Primates. In a scientific sense, it includes the suborder Platyrrhini (New World monkeys) and the family Cercopithecidae (Old World monkeys with tails). However, it does not include great apes or humans, despite their close evolutionary relationship.",
                    "A duck is a general name for several species in the Anatidae family. Ducks are not a single taxonomic group, but they differ from other members of the family, such as geese and swans, by having a smaller body, a shorter neck, and a larger bill.",
                    "Birds are covered in feathers, a trait that distinguishes them from all other living animals. Their forelimbs are modified into wings, enabling most of them to fly."
            };
        }
    }

    // גטרים לגישה למערכים
    public static String[] getNameArray() {
        return nameArray;
    }

    public static String[] getTypeArray() {
        return typeArray;
    }

    public static String[] getDescriptionArray() {
        return descriptionArray;
    }
}