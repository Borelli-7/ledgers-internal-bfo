package de.adorsys.ledgers.postings.domain;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import org.springframework.data.annotation.CreatedDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Entity
@Getter
@ToString
@AllArgsConstructor
public class Posting {

	/*The record id*/
	@Id
	private String id;

	/* The user (technically) recording this posting. */
	private String recUser;

	/* The time of recording of this posting. */
	@CreatedDate
	private LocalDateTime recTime;

	/* The antecedent identifier */
	private String recAntId;

	/*
	 * The hash value of this posting. If is used by the system for integrity
	 * check. A posting is never modified.
	 */
	private String recHash;

	/*
	 * The unique identifier of this business operation. The operation
	 * identifier differs from the posting identifier in that it is not unique.
	 * The same operation, can be repetitively posted if some conditions change.
	 * The operation identifier will always be the sam for all the postings of
	 * an operation. Only one of them will be effective in the account statement
	 * at any given time.
	 */
	private String oprId;

	/* The time of occurrence of this operation. Set by the consuming module. */
	private LocalDateTime oprTime;
	
	/*The hash value of the operation data*/
	private String oprHash;

	/*
	 * The type of operation recorded here. The semantic of this information is
	 * determined by the consuming module.
	 */
	private String oprType;

	/* Details associated with this operation. */
	private String oprDetails;

	/*
	 * This is the time from which the posting is effective in the account
	 * statement. This also differs from the recording time in that the posting
	 * time can be before or after the recording time.
	 * 
	 * If the posting time if before the recording time, it might have an effect
	 * on former postings like past balances. This might lead to the generation
	 * of new postings.
	 * 
	 * The posting time of an adjustment operation at day closing is always the
	 * last second of that day. So event if that operation is posted while still
	 * inside the day, the day closing will be the same. This is, the last
	 * second of that day. In the case of an adjustment operation, the posting
	 * time and the operation time are identical.
	 */
	private LocalDateTime pstTime;

	/*
	 * Some posting are mechanical and do not have an influence on the balance
	 * of an account. Depending on the business logic of the product module,
	 * different types of posting might be defined so that the journal can be
	 * used to document all events associated with an account.
	 * 
	 * For a mechanical posting, the same account and amounts must appear in the
	 * debit and the credit side of the posting. Some account statement will not
	 * display mechanical postings while producing the user statement.
	 */
	private String pstType;

	/*
	 * The Date use to compute interests. This can be different from the posting
	 * date and can lead to the production of other type of balances.
	 */
	private LocalDateTime valTime;

	@ElementCollection
	  @CollectionTable(
	        name="POSTING_LINE",
	        joinColumns=@JoinColumn(name="POSTING_ID")
	  )
	private List<PostingLine> lines;

}
